package controllers

import play.api._
import play.api.mvc._
import models.Absence
import models.ConnectedUsers
import models.FindAllAbsence
import models.CreateAbsence
import models.CreateUser
import models.FindAllUsers
import Play.current
import models.UpdateUser
import net.liftweb.json.Serialization
import models.User
import models.FindAllAbsence
import models.CurrentWeek

object ViewController extends Controller {

  def jsonOk() = Ok("""{"rc":0,"message":"Ok"}""").as("application/json")

  def getCurrentWeek = Action { request =>
    ConnectedUsers.connectedUsersActor ! CurrentWeek(request.session("uuid"))
    jsonOk()
  }

}