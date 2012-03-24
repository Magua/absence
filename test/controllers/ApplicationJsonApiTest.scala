package controllers

import org.specs2.mutable._
import org.specs2.runner
import play.api.test._
import play.api.test.Helpers._
import play.api.mvc.AnyContentAsJson
import play.api.libs.json.Json

class ApplicationJsonApiTest extends Specification {
	"create, list and delete an Absence entry" in {
	  running(FakeApplication()) {
	    val requestJson = Json.parse("""{"description":"Parental leave","start":12345,"end":123456}""")
	    val Some(result) = routeAndCall(FakeRequest(POST, "/jsonNew", FakeHeaders(Map("Content-type" -> Seq("application/json"))), requestJson))
	    println(result)
	    "string" must beEqualTo("string")
	    contentType(result) must beSome("application/json")
	    val content = new String(contentAsBytes(result))
	    println(content)
	    val jsonResponse = Json.parse(content)
	    (jsonResponse \ "rc").as[Int] must beEqualTo(0)
	  }
	}
}