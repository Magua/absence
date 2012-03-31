package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class User(id: Long = -1, name: String) {
  def this(name: String) = this(-1, name);
}

object User {

  def create(u: User): User = {
    DB.withConnection { implicit c =>
      SQL("insert into user (name) values ({name})").on(
        'name -> u.name).executeUpdate()

      val id = SQL("SELECT MAX(id) from user")().collect {
        case Row(id: Int) => id
      }.head

      println("new User created with id: ", id)

      User(id, u.name)
    }
  }
  def all(): List[User] = DB.withConnection { implicit c =>
    SQL("select * from user").as(user *)
  }

  def delete(id: Long) {
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