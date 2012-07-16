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
  lazy val u = User.create(User(name = "Name"))
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
    val absence = Absence(new ObjectId, u.id.toString(), "Parental leave", oneWeekAgo, now)
    val jsonString = Serialization.write[Absence](absence)
    val absenceII = Serialization.read[Absence](jsonString)
    absence.toString() must equalTo(absenceII.toString)
  }

  "make sure serialization works if optional id is missing" in {
    val jsonString = ("""{"userId":"""" + u.id + """","description":"Parental leave","start":432143214321,"end":43254354354344}""")
    val absence = Serialization.read[Absence](jsonString)
    absence.start must equalTo(432143214321L)
  }
  
  "make sure serialization Objectid works" in {
    val oId = new ObjectId
    val jsonString = ("""{"id": """" + oId.toString() + """", "userId":"""" + u.id + """","description":"Parental leave","start":432143214321,"end":43254354354344}""")
    val absence = Serialization.read[Absence](jsonString)
    absence.id must equalTo(oId)
    val jsonStringII = Serialization.write(absence)
  }
}