package models

import com.mongodb.casbah.Imports._
import net.liftweb.json._

class ObjectIdSerializer extends CustomSerializer[ObjectId](format => (
  { 
    case JNothing => new ObjectId
    case JObject(JField("id", JString(s)) :: Nil) => new ObjectId(s)
  },
  { 
    case x: ObjectId => JObject(JField("id", JString(x.toString)) :: Nil)
  }
))