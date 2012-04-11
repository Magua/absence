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
import play.api.libs.concurrent.Promise
import play.api.libs.iteratee.Enumerator
import play.api.libs.iteratee.Iteratee
import play.api.libs.Comet

object Main extends Controller {

  def newSessionId(): String = {
    java.util.UUID.randomUUID().toString()
  }

  /* experimental */
  def comet(sessionId: String) = Action { request =>
    val p: Promise[(Iteratee[String, _], Enumerator[String])] = ConnectedUsers.add(sessionId)
    Async {
      p.orTimeout("Oops", 1000).map { eitherTupleOrTimeout =>
        eitherTupleOrTimeout.fold(
          tuple => Ok.stream(tuple._2 &> Comet(callback = "parent.absenceNS.cometMessage")),
          timeout => InternalServerError(timeout))
      }
    }
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