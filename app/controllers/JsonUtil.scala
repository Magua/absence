package controllers
import play.api.mvc._
import views.html.defaultpages.unauthorized
import net.liftweb.json.Serialization
import models.Absence
import org.codehaus.jackson.annotate.JsonMethod
import models.ConnectedUsers

object JsonUtil {
  def jsonOk(): PlainResult = jsonMessage(0, "Ok")
  def jsonOk(json: String): PlainResult = Results.Ok(json).as("application/json")
  def jsonMessage(rc: Int, message: String): PlainResult = {
    Results.Ok("""{"rc":%1$s,"message":"%2$s"}""".format(rc, message)).as("application/json")
  }
  def jsonError(rc: Int, message: String): PlainResult = {
    Results.InternalServerError("""{"rc":%1$s,"message":"%2$s"}""".format(rc, message)).as("application/json")
  }
  def readJson[T: Manifest](request: Request[play.api.mvc.AnyContent]): T = {
    implicit val formats = net.liftweb.json.DefaultFormats
    val jsonString = request.body.asJson.getOrElse(throw new RuntimeException("Unable to read request.body.asJson")).toString()
    try {
      Serialization.read[T](jsonString)
    } catch {
      case t => throw new RuntimeException("Unable to parse incomming json: " + jsonString)
    }
  }
  def uuid()(implicit r: Request[play.api.mvc.AnyContent]): String = { r.session("uuid") }
}