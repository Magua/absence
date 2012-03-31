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
import play.api.libs.json.JsString

case class User(id: Long = -1, name: String)

object User {
  implicit object UserFormat extends Format[User] {
    def reads(json: JsValue): User = User(
      (json \ "id").asOpt[Long].getOrElse(-1),
      (json \ "name").as[String])
    def writes(a: User): JsValue = JsObject(List(
      "id" -> JsNumber(a.id),
      "name" -> JsString(a.name)))
  }
  def create(u: User): Long = {
    DB.withConnection { implicit c =>
      SQL("insert into user (name) values ({name})").on(
        'name -> u.name).executeUpdate()
      SQL("SELECT MAX(id) from user")().collect {
        case Row(id: Int) => id
      }.head

    }
  }
  def update(u: User): Long = {
	  DB.withConnection { implicit c =>
		  SQL("update user set name={name} where id = {id}").on(
				  'name -> u.name,
				  'id -> u.id).executeUpdate()
	  }
  }
  def all(): List[User] = DB.withConnection { implicit c =>
    SQL("select * from user").as(user *)
  }

  def delete(id: Long): Long = {
    DB.withConnection { implicit c =>
      SQL("delete from user where id = {id}").on(
        'id -> id).executeUpdate()
    }
  }

  val user = {
    get[Long]("id") ~ get[String]("name") map {
      case id ~ name => User(id, name)
    }
  }
}