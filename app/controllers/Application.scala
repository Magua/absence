package controllers

import play.api._
import play.api.mvc._
import models.Absence
import models.User
import play.api.libs.json.Json
import play.api.libs.json.JsValue
import models.ConnectedUsers
import models.GetAllAbsence
import models.CreateNewAbsence
import models.CreateNewUser
import models.GetAllUsers
import Play.current

object Application extends Controller {
  def newSessionId(): String = {
    java.util.UUID.randomUUID().toString()
  }
  def jsonOk() = Ok("""{"rc":0,"message":"Ok"}""").as("application/json")
  def wsTest() = Action { request =>
    val webSocketPort = current.configuration.getString("web.socket.port").getOrElse("900")
    val sessionId = newSessionId()
    Ok(views.html.wstest(webSocketPort, sessionId)).withSession(request.session + ("uuid" -> sessionId))
  }
  def jsonNewUser = Action(parse.json) { request =>
    val user = Json.fromJson[User](request.body)
    ConnectedUsers.connectedUsersActor ! CreateNewUser(request.session("uuid"), user)
    jsonOk()
  }
  def jsonAllUsers = Action(parse.json) { request =>
    ConnectedUsers.connectedUsersActor ! GetAllUsers(request.session("uuid"))
    jsonOk()
  }
  def jsonNew = Action(parse.json) { request =>
    val absence = Json.fromJson[Absence](request.body)
    ConnectedUsers.connectedUsersActor ! CreateNewAbsence(request.session("uuid"), absence)
    jsonOk()
  }
  def jsonAll = Action(parse.json) { request =>
    ConnectedUsers.connectedUsersActor ! GetAllAbsence(request.session("uuid"))
    jsonOk()
  }
  def connect(sessionId: String) = WebSocket.async[JsValue] { request =>
    println("connect ws, sessionId:" + sessionId)
    ConnectedUsers.add(sessionId)
  }
}