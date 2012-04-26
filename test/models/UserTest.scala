package models

import org.specs2.mutable._
import org.specs2.runner
import play.api.test._
import play.api.test.Helpers._
import play.api.mvc.AnyContentAsJson
import play.api.libs.json._
import play.api._
import play.api.mvc._
import net.liftweb.json.Serialization

class UserTest extends Specification {

  implicit val formats = net.liftweb.json.DefaultFormats

  "make sure serialization and deserialisation does not break object" in {
    val u = User(-1, "Dolores Claiborn")

    val jsonString = Serialization.write[User](u)
    println(jsonString)
    val uII = Serialization.read[User](jsonString)
    u must equalTo(uII)
  }

  "make sure serialization works if optional id is missing" in {
    val jsonString = ("""{"name":"Dolores"}""")
    val user = Serialization.read[User](jsonString)
    user.id must equalTo(-1)
  }
  
  "test crud methods" in {
    running(FakeApplication()) {
      val initialSize = User.all().size
      val user = User.create(User(name = "Ove"))
      val sameUser = User.read(user.id).get
      sameUser.id must equalTo(user.id)
      sameUser.name must equalTo("Ove")
      val affectedRows = User.update(User(user.id, "Ove Rytter"))
      affectedRows must equalTo(1)
      
      val updatedUser = User.read(user.id).get
      updatedUser.name must equalTo("Ove Rytter")
      
      val size = User.all().size
      size must equalTo(initialSize + 1)
      
      val effectedDeleteRows = User.delete(updatedUser.id)
      
      val newSize = User.all().size
      newSize must equalTo(initialSize)
    }
  }
}