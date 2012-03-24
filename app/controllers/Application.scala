package controllers

import play.api._
import play.api.mvc._
import models.Absence
import play.api.libs.json.Json

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def jsonNew = Action { request =>
	val absence = request.body.asJson.map(_.as[Absence]).getOrElse(
	  throw new RuntimeException("could not create user")
	)

	val id = Absence.create(absence)
    Ok("""{"rc":0,"message":"Ok"}""").as("application/json")
  }
  def jsonAll = TODO
}