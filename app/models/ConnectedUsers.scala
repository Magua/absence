package models

import scala.collection.mutable.StringBuilder

import com.mongodb.casbah.Imports.ObjectId

import akka.actor.actorRef2Scala
import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Props
import akka.pattern.ask
import akka.util.duration.intToDurationInt
import akka.util.Timeout
import net.liftweb.json.Serialization.write
import play.api.Play.current
import play.api.libs.concurrent.akkaToPlay
import play.api.libs.concurrent.Promise
import play.api.libs.concurrent.Akka
import play.api.libs.iteratee.Iteratee
import play.api.libs.iteratee.Enumerator
import play.api.libs.iteratee.PushEnumerator
import play.api.Logger

object Beacon {
  
  def apply(connectedUsersActor: ActorRef) {
    
    // Create an Iteratee that log all messages to the console.
    val loggerIteratee = Iteratee.foreach[String](event => Logger("beacon").info(event))
    
    implicit val timeout = Timeout(1 second)

    connectedUsersActor ? (Join("beacon")) map {
      case Connected(robotChannel) => 
        // Apply this Enumerator on the logger.
        robotChannel |>> loggerIteratee
    }

    Akka.system.scheduler.schedule(
      60 seconds,
      60 seconds,
      connectedUsersActor,
      BeaconMessage("Beacon...")
    )
  }
  
}
object ConnectedUsers {

  implicit val timeout = Timeout(1 second)
  private lazy val connectedUsersActor = {
    val ref = Akka.system.actorOf(Props[ConnectedUsers])
    Beacon(ref)
    ref
  }

  def add(sessionId: String): Promise[(Iteratee[String, _], Enumerator[String])] = {
    (connectedUsersActor ? Join(sessionId)).asPromise.map {

      case Connected(enumerator) => {
        // Create an Iteratee to consume the feed
        val iteratee = Iteratee.foreach[String] { event =>
          println("unhandled event ", event)
        }.mapDone { _ =>
          println("quit")
          connectedUsersActor ! Quit(sessionId)
        }
        
        (iteratee, enumerator)
      }
    }
  }
  def send(msg: Any): Promise[String] = {
    (connectedUsersActor ? msg).mapTo[String].asPromise
  }
  def toJson(values: Map[String, Any]): String = {
    case class NumberInt(nr: Int)
    case class NumberLong(nr: Long)
    val list = values.map(t => {
      var sb = new StringBuilder()
      sb.append("\"")
      sb append t._1
      sb append "\":"
      sb append (t._2 match {
        case n: Int => n.toString
        case n: Long => n.toString
        case s => "\"" + s + "\""
      })
    })
    list.mkString("{", ",", "}")
  }
}

class ConnectedUsers extends Actor {

  implicit val formats = net.liftweb.json.DefaultFormats

  var users = Map.empty[String, PushEnumerator[String]]
  def receive = {

    case Join(sessionId) => {
      // Create an Enumerator to write to this socket
      val channel = Enumerator.imperative[String](onStart = self ! NotifyJoin(sessionId))
      users = users + (sessionId -> channel)
      println("adding session " + sessionId)
      sender ! Connected(channel)
    }

    case NotifyJoin(sessionId) => {
      println(sessionId + " joined")
    }
    case BeaconMessage(msg) => {
      notifyAll("beacon", Map("time" -> new java.util.Date().getTime()), "all sessions")
    }
    case Quit(sessionId) => {
      users = users - (sessionId)
    }
    case CreateAbsence(sessionId, a) => {
    	val storedAbsence = Absence.create(a)
    	val storedAbsenceJson = write[Absence](storedAbsence)
    	sender ! storedAbsenceJson
    	notifyAll("absence", storedAbsenceJson, sessionId)
    }
    case UpdateAbsence(sessionId, a) => {
    	Absence.update(a)
    	val absenceJson = write[Absence](a)
    	sender ! absenceJson
    	notifyAll("absence", absenceJson, sessionId)
    }
    case DeleteAbsence(sessionId, id) => {
      val affectedRows = Absence.delete(id)
      var json: StringBuffer = new StringBuffer()
      if (affectedRows == 1) {
        json.append(ConnectedUsers.toJson(Map("id" -> id)))
      } else {
        json.append(ConnectedUsers.toJson(Map("error" -> true, "affectedRows" -> affectedRows)))
      }
      sender ! json.toString()
      notifyAll("absenceDelete", json.toString(), sessionId)
    }
    case FindAllAbsence(sessionId) => {
      val all = Absence.all()
      sender ! write[List[Absence]](all)
    }
    case FindAllUsers(sessionId) => {
      sender ! write[List[User]](User.all())
    }
    case CreateUser(sessionId, u) => {
      val storedUser = User.create(u)
      val storedUserJson = write[User](storedUser)
      sender ! storedUserJson
      notifyAll("user", storedUserJson, sessionId)
    }
    case UpdateUser(sessionId, u) => {
      val storedUser = User.update(u)
      val storedUserJson = write[User](u)
      sender ! storedUserJson
      notifyAll("user", storedUserJson, sessionId)
    }
    case DeleteUser(sessionId, id) => {
      val affectedRows = User.delete(id)
      var json: StringBuffer = new StringBuffer()
      if (affectedRows == 1) {
        json.append(ConnectedUsers.toJson(Map("id" -> id)))
      }
      else {
    	json.append(ConnectedUsers.toJson(Map("error" -> true, "affectedRows" -> affectedRows)))        
      }
      sender ! json.toString()
      notifyAll("userDelete", json.toString(), sessionId)
    }
    case CurrentWeek(sessionId) => {
      notify(sessionId, "currentWeek", write[View](View.getCurrentWeek()))
    }
    case a: Any => {
      println(a)
      throw new RuntimeException("Unhandled message: " + a)
    }

  }
  def notify(sessionId: String, name: String, message: String) {
    val option = users.get(sessionId)
    if (option.isDefined) {
      val messageWithName = addJsonName(name, message)
      println("ConnectedUsers.notify sessionId: " + sessionId + " message: " + messageWithName)
      option.get.push(messageWithName)
    } else {
      println("session NOT found " + sessionId)
    }
  }

  def notifyAll(name: String, values: Map[String, Any], exeptThisSessionId: String) {
    notifyAll(name, ConnectedUsers.toJson(values), exeptThisSessionId)
  }
  def notifyAll(name: String, msg: String, exeptThisSessionId: String) {
    val messageWithName = addJsonName(name, msg)
    println("ConnectedUsers.notifyAll(" + users.size + ") message: " + messageWithName)
    users.foreach {
      //case (sessionId, _) if sessionId.equals(exeptThisSessionId) => None
      case (_, channel) => channel.push(messageWithName)
    }
  }
  def addJsonName(name: String, message: String): String = {
    """{"%1$s":%2$s}""" format (name, message)
  }
}
case class BeaconMessage(msg: String)
case class Join(sessionId: String)
case class FindAllAbsence(sessionId: String)
case class FindAllUsers(sessionId: String)
case class CreateAbsence(sessionId: String, abcense: Absence)
case class UpdateAbsence(sessionId: String, abcense: Absence)
case class DeleteAbsence(sessionId: String, id: Long)
case class CreateUser(sessionId: String, u: User)
case class UpdateUser(sessionId: String, u: User)
case class DeleteUser(sessionId: String, id: ObjectId)
case class Quit(sessionId: String)
case class NotifyJoin(sessionId: String)
case class CurrentWeek(sessionId: String)
case class Connected(enumerator: Enumerator[String])