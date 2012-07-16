package models
import org.specs2.mutable.Specification
import play.api.test._
import play.api.test.Helpers._
import anorm._
import org.joda.time.Days
import org.bson.types.ObjectId

class SprintTest extends Specification {

  // -- Date helpers

  def dateIs(date: java.util.Date, str: String) = new java.text.SimpleDateFormat("yyyy-MM-dd").format(date) == str

  def date(str: String) = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(str)

  "Sprint model" should {

    "be retrieved by id" in {

      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        val now = new java.util.Date().getTime()
        val oneWeekAgo = now - (1000 * 60 * 60 * 24 * 7)

        val s1 = Sprint.create(Sprint(new ObjectId, 998, oneWeekAgo, now))

        val Some(sprint) = Sprint.read(s1.id)

        sprint.start must equalTo(oneWeekAgo)
        sprint.end must equalTo(now)
        sprint.sprint_nr must equalTo(998)

      }
    }

  }
}