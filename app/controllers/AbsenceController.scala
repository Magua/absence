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

object AbsenceController extends Controller {
  
  def readAbsence(request: Request[play.api.mvc.AnyContent]): Absence = { 
		  implicit val formats = net.liftweb.json.DefaultFormats
    Serialization.read[Absence](request.body.asJson.getOrElse(throw new RuntimeException).toString())
  }
  
  def jsonOk() = Ok("""{"rc":0,"message":"Ok"}""").as("application/json")

  def newSessionId(): String = {
    java.util.UUID.randomUUID().toString()
  }
  
  def create = Action { request =>
    val absence = readAbsence(request)
    ConnectedUsers.connectedUsersActor ! CreateAbsence(request.session("uuid"), absence)
    jsonOk()
  }
  
  def read(id: Long) = TODO
  
  def update(id: Long) = TODO
  
  def delete(id: Long) = TODO
  
  def findInSprint(sprintId: Long) = TODO

  def findAll = Action { request =>
    ConnectedUsers.connectedUsersActor ! FindAllAbsence(request.session("uuid"))
    jsonOk()
  }
  
}