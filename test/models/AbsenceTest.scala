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
import java.util.Date
import org.specs2.specification.BeforeExample
import java.text.SimpleDateFormat
import java.util.TimeZone
import net.liftweb.json.DefaultFormats
import org.bson.types.ObjectId

class AbsenceTest extends Specification {
  
  val now = new java.util.Date().getTime()
  val oneWeekAgo = now - (1000 * 60 * 60 * 24 * 7)
  def before = User.create(User(name = "Name"))
  def sdf = {
	  val t = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
	  t.setTimeZone(TimeZone.getTimeZone("UTC"))
	  t
  }
  implicit val formats = DefaultFormats + new ObjectIdSerializer

  "make sure simple date format works as expected" in {
    val dString1 = "2012-01-01T20:00:00Z"
    val d1 = sdf.parse(dString1)
    val dString2 = sdf.format(d1)
    dString1 must equalTo(dString2)
  }
  "make sure serialization and deserialisation does not break object" in {
    val absence = Absence(new ObjectId, "432", "Parental leave", oneWeekAgo, now)
    val jsonString = Serialization.write[Absence](absence)
    println("*543'543'543'2543'25432'" + jsonString)
    val absenceII = Serialization.read[Absence](jsonString)
    absence.toString() must equalTo(absenceII.toString)
  }

  "make sure serialization works if optional id is missing" in {
    val jsonString = ("""{"userId":"4321","description":"Parental leave","start":432143214321,"end":43254354354344}""")
    val absence = Serialization.read[Absence](jsonString)
    absence.start must equalTo(432143214321L)
  }

//  "verify absence crudf methods" in {
//    running(FakeApplication()) {
//      val user = before
//      val a = Absence.create(new Absence(user.id, "Frånvaro", oneWeekAgo, now))
//      val aII = Absence.read(a.id).get
//      a must equalTo(aII)
//      Absence.delete(a.id)
//      val shouldBeNone = Absence.read(a.id)
//      shouldBeNone must equalTo(None)
//    }
//  }
}