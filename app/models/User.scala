package models

import play.api.Play.current
import java.util.Date
import com.novus.salat._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._
import se.radley.plugin.salat._
import mongoContext._

case class User(id: ObjectId = new ObjectId, name: String) {}

object User extends ModelCompanion[User, ObjectId] {
  val collection = mongoCollection("users")
  val dao = new SalatDAO[User, ObjectId](collection = collection) {}
def create(u: User): User = {
  collection += grater[User].asDBObject(u)
  u
}
def read(id: ObjectId): Option[User] = dao.findOne(MongoDBObject("id" -> id))
def update(u: User): String = {
  this.create(u)
  u.id.toStringMongod
  }
def all(): List[User] = collection.toList.map(grater[User].asObject(_))
def delete(id: ObjectId): Long = {
  dao.removeById(id)
  1L
}
//  def create(u: User): User = {
//    DB.withConnection { implicit c =>
//      SQL("insert into user (name) values ({name})").on(
//        'name -> u.name).executeUpdate()
//
//      val id = SQL("SELECT MAX(id) from user")().collect {
//        case Row(id: Int) => id
//      }.head
//
//      println("new User created with id: ", id)
//
//      User(id, u.name)
//    }
//  }
//  def read(id: Long): Option[User] = {
//    DB.withConnection { implicit connection =>
//      SQL("select * from user where id = {id}").on(
//        'id -> id
//      ).as(user.singleOpt)
//    }
//  }
//  def update(u: User): Long = {
//	  DB.withConnection { implicit c =>
//		  SQL("update user set name={name} where id = {id}").on(
//				  'name -> u.name,
//				  'id -> u.id).executeUpdate()
//	  }
//  }
//  def all(): List[User] = DB.withConnection { implicit c =>
//    SQL("select * from user").as(user *)
//  }
//
//  def delete(id: Long): Long = {
//    DB.withConnection { implicit c =>
//      SQL("delete from user where id = {id}").on(
//        'id -> id).executeUpdate()
//    }
//  }
//
//  val user = {
//    get[Long]("id") ~ get[String]("name") map {
//      case id ~ name => User(id, name)
//    }
//  }
}