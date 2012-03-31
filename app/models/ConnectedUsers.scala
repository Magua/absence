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
    case UpdateUser(sessionId, u) => {
    	val affectedRows = User.update(u)
    	if (affectedRows.equals(1)) {
    		notifyAll[User]("user", u)
    	}
    	else {
    	  notifyError(sessionId, Seq("id" -> JsString("updateFail"), "affectedRows" -> JsNumber(affectedRows)))
    	}
    }

  }
  def notify[T](sessionId: String, name: String, message: T)(implicit tjs: Writes[T]) {
    val jsonMessage = JsObject(Seq(name -> Json.toJson(message)))
    notifySession(sessionId, jsonMessage)
  }
  def notifyAll[T](name: String, message: T)(implicit tjs: Writes[T]) {
    val jsonMessage = JsObject(Seq(name -> Json.toJson(message)))
    println("ConnectedUsers.notifyAll message: " + jsonMessage)
    users.foreach {
      case (_, channel) => channel.push(jsonMessage)
    }
  }
  def notifyError(sessionId: String, seq: Seq[(String, JsValue)]) {
    val jsObject = JsObject(Seq("error" -> JsObject(seq)))
    notifySession(sessionId, jsObject)
  }
  def notifySession(sessionId: String, jsValue: JsValue) {
    val option = users.get(sessionId)
    if (option.isDefined) {
      println("ConnectedUsers.notify sessionId: " + sessionId + " message: " + jsValue)
      option.get.push(jsValue)
    } else {
      println("session NOT found " + sessionId)
    }    
  }
}
case class Join(sessionId: String)
case class GetAllAbsence(sessionId: String)
case class GetAllUsers(sessionId: String)
case class CreateNewAbsence(sessionId: String, abcense: Absence)
case class CreateNewUser(sessionId: String, u: User)
case class UpdateUser(sessionId: String, u: User)
case class Quit()
case class NotifyJoin()

case class Connected(enumerator: Enumerator[JsValue])
