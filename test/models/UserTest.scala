package models

import org.specs2.mutable._
import org.specs2.runner
import play.api.test._
import play.api.test.Helpers._
import play.api.mvc.AnyContentAsJson
import play.api.libs.json._
import play.api._
import play.api.mvc._

class UserTest extends Specification {
  "make sure serialization and deserialisation does not break object" in {
    val u = User(-1, "Dolores Clayborn")
    val jsonString = Json.toJson(u).toString()
    println(jsonString)
    val uII = Json.parse(jsonString).as[User]
    u must equalTo(uII)
  }
  
  "make sure serialization works if optional id is missing" in {
    val jsonString = ("""{"name":"Dolores"}""")
    val absence = Json.parse(jsonString).as[User]
    absence.id must equalTo(-1)
  }
  
  "test crud methods" in {
    running(FakeApplication()) {
      val id = User.create(User(name = "Ove"))
      val user = User.all()(0)
      user.id must equalTo(id)
      user.name must equalTo("Ove")
      val affectedRows = User.update(User(id, "Ove Rytter"))
      affectedRows must equalTo(1)
      
      val updatedUser = User.all()(0)
      updatedUser.name must equalTo("Ove Rytter")
      
      val size = User.all().size
      size must equalTo(1)
      
      val effectedDeleteRows = User.delete(id)
      effectedDeleteRows must equalTo(1)

      
      val newSize = User.all().size
      newSize must equalTo(0)
    }
  }
}