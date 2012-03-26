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

case class Absence(id: Long = -1, userId: Long, description: String, start: Long, end: Long)

object Absence {
    implicit object AbsenceFormat extends Format[Absence] {
    def reads(json: JsValue): Absence = Absence(
      (json \ "id").asOpt[Long].getOrElse(-1),
      (json \ "userId").as[Long],
      (json \ "description").as[String],
      (json \ "start").as[Long],
      (json \ "end").as[Long])
    def writes(a: Absence): JsValue = JsObject(List(
      "id" -> JsNumber(a.id),
      "userId" -> JsNumber(a.userId),
      "description" -> JsString(a.description),
      "start" -> JsNumber(a.start),
      "end" -> JsNumber(a.end)))
  }
  def create(absence: Absence): Long = {
    DB.withConnection { implicit c =>
      SQL("insert into absence (userId, description, start, end) values ({userId}, {description}, {start}, {end})").on(
    	'userId -> absence.userId,
        'description -> absence.description,
        'start -> absence.start,
        'end -> absence.end).executeUpdate()
      SQL("SELECT MAX(id) from absence")().collect {
        case Row(id: Int) => id
      }.head
          
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
    get[Long]("id") ~ get[Long]("userId") ~ get[String]("description") ~ get[Long]("start") ~ get[Long]("end") map {
      case id ~ userId ~ description ~ start ~ end => Absence(id, userId, description, start, end)
    }
  }
}