package controllers

import org.specs2.mutable._
import org.specs2.runner
import play.api.test._
import play.api.test.Helpers._
import play.api.mvc.AnyContentAsJson
import play.api.libs.json.Json
import models.User
import play.api.mvc.AnyContent
import play.api.mvc.AnyContentAsJson

class ApplicationJsonApiTest extends Specification {
  def makeSureUserIdOneExist() = User.create(User(name = "Name"))
  val now = new java.util.Date().getTime()
  val oneWeekAgo = now - (1000 * 60 * 60 * 24 * 7)
  "Absence CRUDF methods" should {
    "work with correct json" in {
      running(FakeApplication()) {
        makeSureUserIdOneExist()
        val requestJson = Json.parse("""{"userId":1,"description":"Parental leave","start":%1$s,"end":%2$s}""" format(oneWeekAgo, now))
        val json = AnyContentAsJson(requestJson)
        val Some(result) = routeAndCall(FakeRequest(POST, "/absence", FakeHeaders(Map(
          "Content-type" -> Seq("application/json"),
          "Cookie" -> Seq("PLAY_SESSION=f0d369f0c4a27a99ad1a08ec5f62936d082b350a-saidHello%3Ayes%00uuid%3Ae63507f3-f527-476a-980a-84549ba54e1d"))),
          json))
        contentType(result) must beSome("application/json")
        val content = new String(contentAsBytes(result))
        println(content)
        val jsonResponse = Json.parse(content)
        (jsonResponse \ "rc").as[Int] must beEqualTo(0)
      }
    }
    "fail with incorrect json" in {
    	running(FakeApplication()) {
    		makeSureUserIdOneExist()
    		val requestJson = Json.parse("""{"incorrectJson":true}""")
    		val json = AnyContentAsJson(requestJson)
    		val Some(result) = routeAndCall(FakeRequest(POST, "/absence", FakeHeaders(Map(
    				"Content-type" -> Seq("application/json"),
    				"Cookie" -> Seq("PLAY_SESSION=f0d369f0c4a27a99ad1a08ec5f62936d082b350a-saidHello%3Ayes%00uuid%3Ae63507f3-f527-476a-980a-84549ba54e1d"))),
    				json))
    				contentType(result) must beSome("application/json")
    		val content = new String(contentAsBytes(result))
    		println(content)
    		val jsonResponse = Json.parse(content)
    		(jsonResponse \ "rc").as[Int] must beEqualTo(1000)
    	}
    }
  }
}