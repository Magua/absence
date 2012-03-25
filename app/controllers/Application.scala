package controllers

import play.api._
import play.api.mvc._
import models.Absence
import play.api.libs.json.Json
import play.api.libs.json.JsValue
import models.ConnectedUsers
import models.GetAllAbsence
import models.CreateNewAbsence

object Application extends Controller {
  def newSessionId(): String = {
   	java.util.UUID.randomUUID().toString()
  }
  def index = Action { request =>
    Ok(views.html.index("Your new application is ready."))
  }
  def wsTest() = Action { request =>
    val sessionId = newSessionId()
    Ok(views.html.wstest(sessionId)).withSession(
    		request.session + ("uuid" -> sessionId))
  }

  def jsonNew = Action(parse.json) { request =>
    val absence = Json.fromJson[Absence](request.body)
    ConnectedUsers.connectedUsersActor ! CreateNewAbsence(request.session("uuid"), absence)
    Ok("""{"rc":0,"message":"Ok"}""").as("application/json")
  }
  def jsonAll = Action(parse.json) { request =>
    ConnectedUsers.connectedUsersActor ! GetAllAbsence(request.session("uuid"))
    Ok("""{"rc":0,"message":"Ok"}""").as("application/json")
  }

  def connect(sessionId: String) = WebSocket.async[JsValue] { request =>
    println("connect ws")
    ConnectedUsers.add(sessionId)
  }
}