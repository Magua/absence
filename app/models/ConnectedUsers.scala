package models
import play.api.libs.concurrent.Promise
import akka.actor._
import akka.util.duration._
import play.api._
import play.api.libs.iteratee._
import play.api.libs.concurrent._
import akka.util.Timeout
import akka.pattern.ask
import play.api.Play.current
import net.liftweb.json.Serialization._

object ConnectedUsers {

  implicit val timeout = Timeout(1 second)
  lazy val connectedUsersActor = Akka.system.actorOf(Props[ConnectedUsers])

  def add(sessionId: String): Promise[(Iteratee[String, _], Enumerator[String])] = {
    (connectedUsersActor ? Join(sessionId)).asPromise.map {

      case Connected(enumerator) => {
        println(enumerator)
        // Create an Iteratee to consume the feed
        val iteratee = Iteratee.foreach[String] { event =>
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

  implicit val formats = net.liftweb.json.DefaultFormats

  var users = Map.empty[String, PushEnumerator[String]]
  def receive = {

    case Join(sessionId) => {
      // Create an Enumerator to write to this socket
      val channel = Enumerator.imperative[String](onStart = self ! NotifyJoin())
      users = users + (sessionId -> channel)
      println("adding session " + sessionId)
      sender ! Connected(channel)
    }

    case NotifyJoin() => {
    }

    case Quit() => {
    }
    case GetAllAbsence(sessionId) => {
      notify(sessionId, write[List[Absence]](Absence.all()))
    }
    case GetAllUsers(sessionId) => {
      notify(sessionId, write[List[User]](User.all()))
    }
    case CreateNewAbsence(sessionId, a) => {
      val storedAbsence = Absence.create(a)
      notifyAll(write[Absence](storedAbsence))
    }
    case CreateNewUser(sessionId, u) => {
      val storedUser = User.create(u)
      notifyAll(write[User](storedUser))
    }

  }
  def notify(sessionId: String, message: String) {
    val option = users.get(sessionId)
    if (option.isDefined) {
      println("session found notifying")
      option.get.push(message)
    } else {
      println("session NOT found " + sessionId)
    }
  }
  def notifyAll(msg: String) {
    users.foreach {
      case (_, channel) => channel.push(msg)
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

case class Connected(enumerator: Enumerator[String])
