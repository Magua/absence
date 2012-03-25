package controllers

import play.api._
import play.api.mvc._
import models.Absence
import play.api.libs.json.Json
import play.api.libs.json.JsValue
import models.ConnectedUsers
import models.GetAllAbsence

object Application extends Controller {
  def sessionId(request: Request[AnyContent]): String = {
    request.session.get("uuid").getOrElse({
    	val uuid = java.util.UUID.randomUUID().toString()
		request.session + ("uuid", uuid)
        uuid
    })
  }
  // TODO get my head around Scala generics and just use one method for this
  def sessionIdJs(request: Request[JsValue]): String = {
		  request.session.get("uuid").getOrElse({
			  val uuid = java.util.UUID.randomUUID().toString()
					  request.session + ("uuid", uuid)
					  uuid
		  })
  }
  def index = Action { request =>
    Ok(views.html.index("Your new application is ready."))
  }
  def wsTest() = Action { request =>
    Ok(views.html.wstest(sessionId(request)))
  }

  def jsonNew = Action(parse.json) { request =>
    val absence = Json.fromJson[Absence](request.body)
    ConnectedUsers.connectedUsersActor ! absence
    Ok("""{"rc":0,"message":"Ok"}""").as("application/json")
  }
  def jsonAll = Action(parse.json) { request =>
    ConnectedUsers.connectedUsersActor ! GetAllAbsence(sessionIdJs(request))
    Ok("""{"rc":0,"message":"Ok"}""").as("application/json")
  }

  def connect(sessionId: String) = WebSocket.async[JsValue] { request =>
    println("connect ws")
    ConnectedUsers.add(sessionId)
  }
}