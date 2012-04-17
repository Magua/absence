package models
import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import java.util.Date

case class Absence(id: Long = -1, userId: Long, description: String, start: Long, end: Long) {
  def this(userId: Long, description: String, start: Long, end: Long) = this(-1, userId, description, start, end)
}

object Absence {
  def create(absence: Absence): Absence = {
    DB.withConnection { implicit c =>
      SQL("insert into absence (userId, description, start, end) values ({userId}, {description}, {start}, {end})").on(
        'userId -> absence.userId,
        'description -> absence.description,
        'start -> absence.start,
        'end -> absence.end).executeUpdate()

      val id = SQL("SELECT MAX(id) from absence")().collect {
        case Row(id: Int) => id
      }.head

      Absence(id, absence.userId, absence.description, absence.start, absence.end)
    }
  }
  def read(id: Long): Option[Absence] = {
    DB.withConnection { implicit connection =>
      SQL("select * from absence where id = {id}").on(
        'id -> id
      ).as(absence.singleOpt)
    }
  }
  def update(a: Absence): Long = {
    DB.withConnection { implicit c =>
      SQL("update absence set description={description}, start={start}, end={end} where id = {id}").on(
        'description -> a.description,
        'start -> a.start,
        'end -> a.end,
        'id -> a.id).executeUpdate()
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