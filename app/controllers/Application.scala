package controllers

import play.api._
import play.api.mvc._
import models.Absence
import play.api.libs.json.Json

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def jsonNew = Action(parse.json) { request =>
    val absence = Json.fromJson[Absence](request.body)
	val id = Absence.create(absence)
    Ok("""{"rc":0,"message":"Ok"}""").as("application/json")
  }
  def jsonAll = TODO
}