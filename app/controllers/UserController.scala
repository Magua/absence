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

object UserController extends Controller {
  
  def readUser(request: Request[play.api.mvc.AnyContent]): User = { 
		  implicit val formats = net.liftweb.json.DefaultFormats
		  Serialization.read[User](request.body.asJson.getOrElse(throw new RuntimeException).toString())
  }
  
  def jsonOk() = Ok("""{"rc":0,"message":"Ok"}""").as("application/json")

  def newSessionId(): String = {
    java.util.UUID.randomUUID().toString()
  }
  
  def create = Action { request =>
    val user = readUser(request)
    ConnectedUsers.connectedUsersActor ! CreateUser(request.session("uuid"), user)
    jsonOk()
  }
  
  def read(id: Long) = TODO
  
  def update = Action { request =>
    val user = readUser(request)
    ConnectedUsers.connectedUsersActor ! UpdateUser(request.session("uuid"), user)
    jsonOk()
  }
  
  def delete(id: Long) = TODO
  
  def findAll = Action { request =>
    ConnectedUsers.connectedUsersActor ! FindAllUsers(request.session("uuid"))
    jsonOk()
  }
  
}