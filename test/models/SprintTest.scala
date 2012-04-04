package models
import org.specs2.mutable.Specification

import play.api.test._
import play.api.test.Helpers._

import anorm._

class SprintTest extends Specification {

  // -- Date helpers

  def dateIs(date: java.util.Date, str: String) = new java.text.SimpleDateFormat("yyyy-MM-dd").format(date) == str

  def date(str: String) = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(str)

  "Sprint model" should {

    "be retrieved by id" in {

      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {

        if (Sprint.findAll.isEmpty) {

          Seq(
            Sprint(Id(1), 998, Some(date("2012-07-01")), Some(date("2012-08-01"))),
            Sprint(Id(2), 999, Some(date("2012-01-01")), Some(date("2012-01-08")))).foreach(Sprint.create)

        }

        val Some(sprint) = Sprint.findById(2)

        sprint.start_date must beSome.which(dateIs(_, "2012-01-01"))
        sprint.end_date must beSome.which(dateIs(_, "2012-01-08"))
        sprint.sprint_nr must equalTo(999)

      }
    }

  }
}