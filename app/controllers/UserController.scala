package controllers

import play.api._
import play.api.mvc._
import models._
import Play.current
import net.liftweb.json.Serialization
import controllers.JsonUtil._

object UserController extends Controller {
  
  def create = Action { implicit request =>
    Async {
      val u = readJson[User](request)
      ConnectedUsers.send(CreateUser(uuid, u)).map { response =>
        jsonOk(response)
      }
    }
  }
  
  def read(id: Long) = TODO
  
  def update = Action { implicit request =>
    Async {
      val u = readJson[User](request)
      ConnectedUsers.send(UpdateUser(uuid, u)).map { response =>
        jsonOk(response)
      }
    }
  }
  
  def delete(id: Long) = Action { implicit request =>
    Async {
      ConnectedUsers.send(DeleteUser(uuid, id)).map { response =>
        jsonOk(response)
      }
    }
  }
  
  def findAll = Action { implicit request =>
    Async {
      ConnectedUsers.send(FindAllUsers(uuid)).map { response =>
        jsonOk(response)
      }
    }
  }
  
}