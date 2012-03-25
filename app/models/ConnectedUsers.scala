package models
import play.api.libs.concurrent.Promise
import akka.actor._
import akka.util.duration._

import play.api._
import play.api.libs.json._
import play.api.libs.iteratee._
import play.api.libs.concurrent._

import akka.util.Timeout
import akka.pattern.ask

import play.api.Play.current

object ConnectedUsers {
  implicit val timeout = Timeout(1 second)
  lazy val connectedUsersActor = Akka.system.actorOf(Props[ConnectedUsers])

  def add(sessionId: String): Promise[(Iteratee[JsValue, _], Enumerator[JsValue])] = {
    (connectedUsersActor ? Join(sessionId)).asPromise.map {

      case Connected(enumerator) => {
    	println(enumerator)
        // Create an Iteratee to consume the feed
        val iteratee = Iteratee.foreach[JsValue] { event =>
        	println("unhandled event ", event)
        }.mapDone { _ =>
        println("quit")
          connectedUsersActor ! Quit()
        }

        (iteratee, enumerator)
      }
    }
  }
}

class ConnectedUsers extends Actor {
  var users = Map.empty[String, PushEnumerator[JsValue]]
  def receive = {

    case Join(sessionId) => {
      // Create an Enumerator to write to this socket
      val channel = Enumerator.imperative[JsValue](onStart = self ! NotifyJoin())
      users = users + (sessionId -> channel)

      sender ! Connected(channel)
    }

    case NotifyJoin() => {
      notifyAll("join", "has entered the room")
    }

    case Talk(text) => {
      println("talk {0}", text)
      notifyAll("talk", text)
    }

    case Quit() => {
      notifyAll("quit", "has leaved the room")
    }
    
    case Absence(id, description, from, to) => {
      val id = Absence.create(Absence(-1, description, from, to))
      println("new absence created with id: ", id)
      
    }

  }
  def notify(sessionId: String, message: JsObject) {
    val option = users.get(sessionId)
    if (option.isDefined) {
      option.get.push(message)
    }
  }
  def notifyAll(kind: String, text: String) {
    val msg = JsObject(
      Seq(
        "kind" -> JsString(kind),
        "message" -> JsString(text)))
    users.foreach {
      case (_, channel) => channel.push(msg)
    }
  }
}
case class Join(sessionId: String)
case class GetAllAbsence(sessionId: String)
case class Quit()
case class Talk(text: String)
case class NotifyJoin()

case class Connected(enumerator: Enumerator[JsValue])
