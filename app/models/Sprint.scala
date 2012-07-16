package models

import java.util.{Date}

import play.api.Play.current
import com.mongodb.casbah.Imports._
import com.novus.salat._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.novus.salat.global._
import se.radley.plugin.salat._
import mongoContext._


case class Sprint(id: ObjectId, sprint_nr: Long, start: Long, end: Long) 

object Sprint{
  val collection = mongoCollection("sprints")
  val dao = new SalatDAO[Sprint, ObjectId](collection = collection) {}

  def create(a: Sprint): Sprint = {
    val id = dao.insert(a).get
    a.copy(id = id)
  }
  def read(id: ObjectId): Option[Sprint] = dao.findOneById(id)
  def update(a: Sprint): Int = {
    dao.save(a)
    1
  }
  def all(): List[Sprint] = collection.toList.map(grater[Sprint].asObject(_))
  def delete(id: ObjectId): Int = {
    dao.removeById(id)
    1
  }
  
}