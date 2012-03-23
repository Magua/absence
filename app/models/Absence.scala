package models
import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import play.api.libs.json.JsValue
import play.api.libs.json.Format
import play.api.libs.json.JsObject
import play.api.libs.json.JsNumber
import play.api.libs.json.JsString

case class Absence(id: Long = -1, description: String, start: Long, end: Long)

object Absence {
    implicit object AbsenceFormat extends Format[Absence] {
    def reads(json: JsValue): Absence = Absence(
      (json \ "id").asOpt[Long].getOrElse(-1),
      (json \ "description").as[String],
      (json \ "start").as[Long],
      (json \ "end").as[Long])
    def writes(a: Absence): JsValue = JsObject(List(
      "id" -> JsNumber(a.id),
      "description" -> JsString(a.description),
      "start" -> JsNumber(a.start),
      "end" -> JsNumber(a.end)))
  }
  def create(absence: Absence) {
    DB.withConnection { implicit c =>
      SQL("insert into absence (description, start, end) values ({description}, {start}, {end})").on(
        'description -> absence.description,
        'start -> absence.start,
        'end -> absence.end).executeUpdate()
    }
  }
  def all(): List[Absence] = DB.withConnection { implicit c =>
    SQL("select * from absence").as(absence *)
  }

  def delete(id: Long) {
    DB.withConnection { implicit c =>
      SQL("delete from absence where id = {id}").on(
        'id -> id).executeUpdate()
    }
  }

  val absence = {
    get[Long]("id") ~ get[String]("description") ~ get[Long]("start") ~ get[Long]("end") map {
      case id ~ description ~ start ~ end => Absence(id, description, start, end)
    }
  }
}