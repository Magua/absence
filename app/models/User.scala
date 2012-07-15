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
    val id = dao.insert(u).get
    new User(id, u.name)
  }
  def read(id: ObjectId): Option[User] = dao.findOneById(id)
  def update(u: User): Int = {
    dao.save(u)
    1
  }
  def all(): List[User] = collection.toList.map(grater[User].asObject(_))
  def delete(id: ObjectId): Long = {
    dao.removeById(id)
    1L
  }
}