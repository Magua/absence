package controllers

import play.api._
import play.api.mvc._
import models._
import Play.current
import models.UpdateUser
import controllers.JsonUtil._
import net.liftweb.json.Serialization
import org.bson.types.ObjectId

object AbsenceController extends Controller {

  def create = Action { implicit request =>
    Async {
      val a = readJson[Absence](request)
      ConnectedUsers.send(CreateAbsence(uuid, a)).map { response =>
        jsonOk(response)
      }
    }
  }
  
  def read(id: String) = TODO
  
  def update = Action { implicit request =>
    Async {
      val a = readJson[Absence](request)
      ConnectedUsers.send(UpdateAbsence(uuid, a)).map { response =>
        jsonOk(response)
      }
    }
  }
  
  def delete(id: String) = Action { implicit request =>
    Async {
      ConnectedUsers.send(DeleteAbsence(uuid, new ObjectId(id))).map { response =>
        jsonOk(response)
      }
    }
  }
  
  def findInSprint(sprintId: Long) = TODO

  def findAll = Action { implicit request =>
     Async {
      ConnectedUsers.send( FindAllAbsence(uuid)).map { response =>
        jsonOk(response)
      }
    }
  }
  
}