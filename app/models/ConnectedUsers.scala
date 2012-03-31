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
      println("adding session " + sessionId)
      sender ! Connected(channel)
    }

    case NotifyJoin() => {
    }

    case Quit() => {
    }
    case GetAllAbsence(sessionId) => {
      notify[List[Absence]](sessionId, "absenceList", Absence.all())
    }
    case GetAllUsers(sessionId) => {
    	notify[List[User]](sessionId, "userList", User.all())
    }
    case CreateNewAbsence(sessionId, a) => {
      val id = Absence.create(Absence(-1, a.userId, a.description, a.start, a.end))
      notifyAll[Absence]("absence", Absence(id, a.userId, a.description, a.start, a.end))
    }
    case CreateNewUser(sessionId, u) => {
      val id = User.create(User(name = u.name))
      notifyAll[User]("user", User(id, u.name))
    }

  }
  def notify[T](sessionId: String, name: String, message: T)(implicit tjs: Writes[T]) {
    val jsonMessage = JsObject(Seq(name -> Json.toJson(message)))
    val option = users.get(sessionId)
    if (option.isDefined) {
      println("ConnectedUsers.notify sessionId: " + sessionId + " message: " + jsonMessage)
      option.get.push(jsonMessage)
    } else {
      println("session NOT found " + sessionId)
    }
  }
  def notifyAll[T](name: String, message: T)(implicit tjs: Writes[T]) {
    val jsonMessage = JsObject(Seq(name -> Json.toJson(message)))
    println("ConnectedUsers.notifyAll message: " + jsonMessage)
    users.foreach {
      case (_, channel) => channel.push(jsonMessage)
    }
  }
}
case class Join(sessionId: String)
case class GetAllAbsence(sessionId: String)
case class GetAllUsers(sessionId: String)
case class CreateNewAbsence(sessionId: String, abcense: Absence)
case class CreateNewUser(sessionId: String, u: User)
case class Quit()
case class NotifyJoin()

case class Connected(enumerator: Enumerator[JsValue])
