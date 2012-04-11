package controllers

import play.api._
import play.api.mvc._
import models._
import Play.current
import models.UpdateUser
import controllers.JsonUtil._
import net.liftweb.json.Serialization

object AbsenceController extends Controller {

  def create = Action { implicit request =>
    handle[Absence](action = (a: Absence) => {
    	ConnectedUsers.connectedUsersActor ! CreateAbsence(uuid, a)
    })
  }
  
  def read(id: Long) = TODO
  
  def update = Action { implicit request =>
    handle[Absence](action = (a: Absence) => {
    	ConnectedUsers.connectedUsersActor ! UpdateAbsence(uuid, a)            
    })
  }
  
  def delete(id: Long) = Action { implicit request =>
    ConnectedUsers.connectedUsersActor ! DeleteAbsence(uuid, id)
    jsonOk()
  }
  
  def findInSprint(sprintId: Long) = TODO

  def findAll = Action { implicit request =>
    ConnectedUsers.connectedUsersActor ! FindAllAbsence(uuid)
    jsonOk()
  }
  
}