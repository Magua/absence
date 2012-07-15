package models

import org.specs2.mutable.Specification
import play.api.test.Helpers.running
import play.api.test.FakeApplication
import com.mongodb.casbah.Imports._
import net.liftweb.json.Serialization

class UserTest extends Specification {

  implicit val formats = net.liftweb.json.DefaultFormats

  "make sure serialization and deserialisation does not break object" in {
//    val u = User(new ObjectId, "dallas")
//
//    val jsonString = User.toJson(u).toString()
//    println(jsonString)
//    val uII = User.fromJSON(jsonString)
//    u must equalTo(uII)
  }

  "make sure serialization works if optional id is missing" in {
    val jsonString = ("""{"name":"Dolores"}""")
    val user = Serialization.read[User](jsonString)
    user.name must equalTo("Dolores")
  }
  
  "test crud methods" in {
    running(FakeApplication()) {
      val initialSize = User.all().size
      println(initialSize)
      val user = User.create(User(name = "Ove"))
      println(user)
      val sameUser = User.read(user.id).get
      sameUser.id must equalTo(user.id)
      sameUser.name must equalTo("Ove")
      val affectedRows = User.update(User(user.id, "Ove Rytter"))
      affectedRows must equalTo(1)
      
      val updatedUser = User.read(user.id).get
      updatedUser.name must equalTo("Ove Rytter")
      
      val size = User.all().size
      size must equalTo(initialSize + 1)
      
      User.delete(updatedUser.id)
      
      val newSize = User.all().size
      newSize must equalTo(initialSize)
    }
  }
}