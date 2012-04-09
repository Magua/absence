package models
import org.specs2.mutable._

class ConnectedUsersTest extends Specification {
  "make sure json messages with integers are stringified without quotations" in {
    val jsonString: String = ConnectedUsers.toJson(Map("name" -> "ove", "number" -> 230))
    jsonString.toString() must equalTo("""{"name":"ove","number":230}""")
  }
}