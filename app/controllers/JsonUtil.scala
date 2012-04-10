package controllers
import play.api.mvc._
import views.html.defaultpages.unauthorized
import net.liftweb.json.Serialization
import models.Absence
import org.codehaus.jackson.annotate.JsonMethod

object JsonUtil {
  def jsonOk(): PlainResult = jsonMessage(0, "Ok")
  def jsonMessage(rc: Int, message: String): PlainResult = {
    Results.Ok("""{"rc":%1$s,"message":"%2$s"}""".format(rc, message)).as("application/json")
  }
  def readJson[T: Manifest](request: Request[play.api.mvc.AnyContent]): Option[T] = {
    implicit val formats = net.liftweb.json.DefaultFormats
    try {
      Some(Serialization.read[T](request.body.asJson.getOrElse(throw new RuntimeException).toString()))
    } catch {
      case t => None
    }
  }
  def handle[T](
    action: T => Unit,
    success: Unit => PlainResult = { _ => { JsonUtil.jsonMessage(0, "Ok") } },
    error: Exception => PlainResult = { _ => JsonUtil.jsonMessage(1000, "Not ok!?!") })
    	(implicit r: Request[play.api.mvc.AnyContent], mt: Manifest[T]): Result = {
    try {
      val model = JsonUtil.readJson[T](r).get
      action(model)
      success()
    } catch {
      case e: Exception => error(e)
    }
  }
  def uuid()(implicit r: Request[play.api.mvc.AnyContent]): String = { r.session("uuid") }
}