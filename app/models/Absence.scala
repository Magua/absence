package models

import play.api.Play.current
import java.util.Date
import com.mongodb.casbah.Imports._
import com.novus.salat._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.novus.salat.global._
import se.radley.plugin.salat._
import mongoContext._

case class Absence(id: ObjectId = new ObjectId, userId: String, description: String, start: Long, end: Long) {}

object Absence {
  val collection = mongoCollection("absences")
  val dao = new SalatDAO[Absence, ObjectId](collection = collection) {}
  def create(a: Absence): Absence = {
    val id = dao.insert(a).get
    a.copy(id = id)
  }
  def read(id: ObjectId): Option[Absence] = dao.findOneById(id)
  def update(a: Absence): Int = {
    dao.save(a)
    1
  }
  def all(): List[Absence] = collection.toList.map(grater[Absence].asObject(_))
  def delete(id: ObjectId): Int = {
    dao.removeById(id)
    1
  }
}