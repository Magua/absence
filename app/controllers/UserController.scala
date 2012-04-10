package controllers

import play.api._
import play.api.mvc._
import models._
import Play.current
import net.liftweb.json.Serialization
import controllers.JsonUtil._

object UserController extends Controller {
  
  def create = Action { implicit request =>
    handle[User](action = (user: User) => {
    	ConnectedUsers.connectedUsersActor ! CreateUser(uuid, user)
    })
  }
  
  def read(id: Long) = TODO
  
  def update = Action { implicit request =>
    handle[User](action = (user: User) => {
    	ConnectedUsers.connectedUsersActor ! UpdateUser(uuid, user)
    })
  }
  
  def delete(id: Long) = Action { implicit request =>
    ConnectedUsers.connectedUsersActor ! DeleteUser(uuid, id)
    jsonOk()
  }
  
  def findAll = Action { implicit request =>
    ConnectedUsers.connectedUsersActor ! FindAllUsers(uuid)
    jsonOk()
  }
  
}