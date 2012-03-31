package controllers

import play.api._
import play.api.mvc._
import models.Absence
import models.User
import models.ConnectedUsers
import models.GetAllAbsence
import models.CreateNewAbsence
import models.CreateNewUser
import models.GetAllUsers
import net.liftweb.json.Serialization._

object Application extends Controller {

  implicit val formats = net.liftweb.json.DefaultFormats

  def newSessionId(): String = {
    java.util.UUID.randomUUID().toString()
  }
  def jsonOk() = Ok("""{"rc":0,"message":"Ok"}""").as("application/json")
  def wsTest() = Action { request =>
    val sessionId = newSessionId()
    Ok(views.html.wstest(sessionId)).withSession(request.session + ("uuid" -> sessionId))
  }
  def jsonNewUser = Action(parse.json) { request =>
    val user = read[User](request.body.toString)
    ConnectedUsers.connectedUsersActor ! CreateNewUser(request.session("uuid"), user)
    jsonOk()
  }
  def jsonAllUsers = Action(parse.json) { request =>
    ConnectedUsers.connectedUsersActor ! GetAllUsers(request.session("uuid"))
    jsonOk()
  }
  def jsonNew = Action(parse.json) { request =>
    val absence = read[Absence](request.body.toString)
    ConnectedUsers.connectedUsersActor ! CreateNewAbsence(request.session("uuid"), absence)
    jsonOk()
  }
  def jsonAll = Action(parse.json) { request =>
    ConnectedUsers.connectedUsersActor ! GetAllAbsence(request.session("uuid"))
    jsonOk()
  }
  def connect(sessionId: String) = WebSocket.async[String] { request =>
    println("connect ws, sessionId:" + sessionId)
    ConnectedUsers.add(sessionId)
  }
}