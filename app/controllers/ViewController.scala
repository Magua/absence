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
import controllers.JsonUtil._

object ViewController extends Controller {

  def getCurrentWeek = Action { implicit request =>
    ConnectedUsers.connectedUsersActor ! CurrentWeek(uuid)
    jsonOk()
  }

}