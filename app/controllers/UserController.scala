package controllers

import com.mongodb.casbah.Imports.ObjectId
import controllers.JsonUtil.jsonOk
import controllers.JsonUtil.readJson
import controllers.JsonUtil.uuid
import models.ConnectedUsers
import models.CreateUser
import models.DeleteUser
import models.FindAllUsers
import models.UpdateUser
import models.User
import play.api.mvc.Action
import play.api.mvc.Controller


object UserController extends Controller {
  
  def create = Action { implicit request =>
    Async {
      val u = readJson[User](request)
      ConnectedUsers.send(CreateUser(uuid, u)).map { response =>
        jsonOk(response)
      }
    }
  }
  
  def read(id: String) = TODO
  
  def update = Action { implicit request =>
    Async {
      val u = readJson[User](request)
      ConnectedUsers.send(UpdateUser(uuid, u)).map { response =>
        jsonOk(response)
      }
    }
  }
  
  def delete(id: String) = Action { implicit request =>
    Async {
      ConnectedUsers.send(DeleteUser(uuid, new ObjectId(id))).map { response =>
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