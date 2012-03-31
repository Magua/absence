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
    val u = User(-1, "Dolores Clayborn")

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
}