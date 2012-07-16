package models

import com.mongodb.casbah.Imports._
import net.liftweb.json._

class ObjectIdSerializer extends CustomSerializer[ObjectId](format => (
  { 
    case JNothing => new ObjectId
    case JString(s) => new ObjectId(s.toString())
    case x => throw new RuntimeException("(to objectid) unable to map " + x)
  },
  { 
    case x: ObjectId => JString(x.toString)
  }
))