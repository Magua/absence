package controllers

import play.api._
import play.api.mvc._
import models.Absence
import models.User
import models.ConnectedUsers
import models.FindAllAbsence
import models.CreateAbsence
import models.CreateUser
import models.FindAllUsers
import Play.current
import models.UpdateUser
import net.liftweb.json.Serialization

object Main extends Controller {

  def newSessionId(): String = {
    java.util.UUID.randomUUID().toString()
  }
  
  def wsTest() = Action { request =>
    val webSocketPort = current.configuration.getString("web.socket.port").getOrElse("900")
    val sessionId = newSessionId()
    Ok(views.html.wstest(webSocketPort, sessionId)).withSession(request.session + ("uuid" -> sessionId))
  }

  def connect(sessionId: String) = WebSocket.async[String] { request =>
    println("connect ws, sessionId:" + sessionId)
    ConnectedUsers.add(sessionId)
  }
}