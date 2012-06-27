// @SOURCE:/Users/magnus/Projects/git/absence/conf/routes
// @HASH:fe698f2315e86fb85d47aaccbae3561eef5a5d6d
// @DATE:Thu May 10 21:50:19 CEST 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {


// @LINE:6
val controllers_Main_absence0 = Route("GET", PathPattern(List(StaticPart("/"))))
                    

// @LINE:9
val controllers_Main_connect1 = Route("GET", PathPattern(List(StaticPart("/connect/"),DynamicPart("sessionId", """[^/]+"""))))
                    

// @LINE:10
val controllers_Main_comet2 = Route("GET", PathPattern(List(StaticPart("/comet/"),DynamicPart("sessionId", """[^/]+"""))))
                    

// @LINE:11
val controllers_Main_serverSentEvents3 = Route("GET", PathPattern(List(StaticPart("/serverSentEvents/"),DynamicPart("sessionId", """[^/]+"""))))
                    

// @LINE:14
val controllers_UserController_create4 = Route("POST", PathPattern(List(StaticPart("/user"))))
                    

// @LINE:15
val controllers_UserController_read5 = Route("GET", PathPattern(List(StaticPart("/user/"),DynamicPart("id", """[^/]+"""))))
                    

// @LINE:16
val controllers_UserController_update6 = Route("PUT", PathPattern(List(StaticPart("/user"))))
                    

// @LINE:17
val controllers_UserController_delete7 = Route("DELETE", PathPattern(List(StaticPart("/user/"),DynamicPart("id", """[^/]+"""))))
                    

// @LINE:18
val controllers_UserController_findAll8 = Route("GET", PathPattern(List(StaticPart("/user"))))
                    

// @LINE:20
val controllers_AbsenceController_create9 = Route("POST", PathPattern(List(StaticPart("/absence"))))
                    

// @LINE:21
val controllers_AbsenceController_read10 = Route("GET", PathPattern(List(StaticPart("/absence/"),DynamicPart("id", """[^/]+"""))))
                    

// @LINE:22
val controllers_AbsenceController_update11 = Route("PUT", PathPattern(List(StaticPart("/absence"))))
                    

// @LINE:23
val controllers_AbsenceController_delete12 = Route("DELETE", PathPattern(List(StaticPart("/absence/"),DynamicPart("id", """[^/]+"""))))
                    

// @LINE:24
val controllers_AbsenceController_findAll13 = Route("GET", PathPattern(List(StaticPart("/absence"))))
                    

// @LINE:25
val controllers_AbsenceController_findInSprint14 = Route("GET", PathPattern(List(StaticPart("/absenceInSprint/"),DynamicPart("sprintId", """[^/]+"""))))
                    

// @LINE:27
val controllers_SprintController_create15 = Route("POST", PathPattern(List(StaticPart("/sprint"))))
                    

// @LINE:28
val controllers_SprintController_read16 = Route("GET", PathPattern(List(StaticPart("/sprint/"),DynamicPart("id", """[^/]+"""))))
                    

// @LINE:29
val controllers_SprintController_update17 = Route("PUT", PathPattern(List(StaticPart("/sprint"))))
                    

// @LINE:30
val controllers_SprintController_delete18 = Route("DELETE", PathPattern(List(StaticPart("/sprint/"),DynamicPart("id", """[^/]+"""))))
                    

// @LINE:31
val controllers_SprintController_findCurrent19 = Route("GET", PathPattern(List(StaticPart("/sprintCurrent"))))
                    

// @LINE:33
val controllers_ViewController_getCurrentWeek20 = Route("GET", PathPattern(List(StaticPart("/view/current"))))
                    

// @LINE:35
val controllers_Main_scroll21 = Route("GET", PathPattern(List(StaticPart("/scroll"))))
                    

// @LINE:38
val controllers_Assets_at22 = Route("GET", PathPattern(List(StaticPart("/assets/"),DynamicPart("file", """.+"""))))
                    
def documentation = List(("""GET""","""/""","""controllers.Main.absence"""),("""GET""","""/connect/$sessionId<[^/]+>""","""controllers.Main.connect(sessionId:String)"""),("""GET""","""/comet/$sessionId<[^/]+>""","""controllers.Main.comet(sessionId:String)"""),("""GET""","""/serverSentEvents/$sessionId<[^/]+>""","""controllers.Main.serverSentEvents(sessionId:String)"""),("""POST""","""/user""","""controllers.UserController.create"""),("""GET""","""/user/$id<[^/]+>""","""controllers.UserController.read(id:Long)"""),("""PUT""","""/user""","""controllers.UserController.update"""),("""DELETE""","""/user/$id<[^/]+>""","""controllers.UserController.delete(id:Long)"""),("""GET""","""/user""","""controllers.UserController.findAll"""),("""POST""","""/absence""","""controllers.AbsenceController.create"""),("""GET""","""/absence/$id<[^/]+>""","""controllers.AbsenceController.read(id:Long)"""),("""PUT""","""/absence""","""controllers.AbsenceController.update"""),("""DELETE""","""/absence/$id<[^/]+>""","""controllers.AbsenceController.delete(id:Long)"""),("""GET""","""/absence""","""controllers.AbsenceController.findAll"""),("""GET""","""/absenceInSprint/$sprintId<[^/]+>""","""controllers.AbsenceController.findInSprint(sprintId:Long)"""),("""POST""","""/sprint""","""controllers.SprintController.create"""),("""GET""","""/sprint/$id<[^/]+>""","""controllers.SprintController.read(id:Long)"""),("""PUT""","""/sprint""","""controllers.SprintController.update"""),("""DELETE""","""/sprint/$id<[^/]+>""","""controllers.SprintController.delete(id:Long)"""),("""GET""","""/sprintCurrent""","""controllers.SprintController.findCurrent"""),("""GET""","""/view/current""","""controllers.ViewController.getCurrentWeek"""),("""GET""","""/scroll""","""controllers.Main.scroll"""),("""GET""","""/assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""))
             
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Main_absence0(params) => {
   call { 
        invokeHandler(_root_.controllers.Main.absence, HandlerDef(this, "controllers.Main", "absence", Nil))
   }
}
                    

// @LINE:9
case controllers_Main_connect1(params) => {
   call(params.fromPath[String]("sessionId", None)) { (sessionId) =>
        invokeHandler(_root_.controllers.Main.connect(sessionId), HandlerDef(this, "controllers.Main", "connect", Seq(classOf[String])))
   }
}
                    

// @LINE:10
case controllers_Main_comet2(params) => {
   call(params.fromPath[String]("sessionId", None)) { (sessionId) =>
        invokeHandler(_root_.controllers.Main.comet(sessionId), HandlerDef(this, "controllers.Main", "comet", Seq(classOf[String])))
   }
}
                    

// @LINE:11
case controllers_Main_serverSentEvents3(params) => {
   call(params.fromPath[String]("sessionId", None)) { (sessionId) =>
        invokeHandler(_root_.controllers.Main.serverSentEvents(sessionId), HandlerDef(this, "controllers.Main", "serverSentEvents", Seq(classOf[String])))
   }
}
                    

// @LINE:14
case controllers_UserController_create4(params) => {
   call { 
        invokeHandler(_root_.controllers.UserController.create, HandlerDef(this, "controllers.UserController", "create", Nil))
   }
}
                    

// @LINE:15
case controllers_UserController_read5(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(_root_.controllers.UserController.read(id), HandlerDef(this, "controllers.UserController", "read", Seq(classOf[Long])))
   }
}
                    

// @LINE:16
case controllers_UserController_update6(params) => {
   call { 
        invokeHandler(_root_.controllers.UserController.update, HandlerDef(this, "controllers.UserController", "update", Nil))
   }
}
                    

// @LINE:17
case controllers_UserController_delete7(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(_root_.controllers.UserController.delete(id), HandlerDef(this, "controllers.UserController", "delete", Seq(classOf[Long])))
   }
}
                    

// @LINE:18
case controllers_UserController_findAll8(params) => {
   call { 
        invokeHandler(_root_.controllers.UserController.findAll, HandlerDef(this, "controllers.UserController", "findAll", Nil))
   }
}
                    

// @LINE:20
case controllers_AbsenceController_create9(params) => {
   call { 
        invokeHandler(_root_.controllers.AbsenceController.create, HandlerDef(this, "controllers.AbsenceController", "create", Nil))
   }
}
                    

// @LINE:21
case controllers_AbsenceController_read10(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(_root_.controllers.AbsenceController.read(id), HandlerDef(this, "controllers.AbsenceController", "read", Seq(classOf[Long])))
   }
}
                    

// @LINE:22
case controllers_AbsenceController_update11(params) => {
   call { 
        invokeHandler(_root_.controllers.AbsenceController.update, HandlerDef(this, "controllers.AbsenceController", "update", Nil))
   }
}
                    

// @LINE:23
case controllers_AbsenceController_delete12(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(_root_.controllers.AbsenceController.delete(id), HandlerDef(this, "controllers.AbsenceController", "delete", Seq(classOf[Long])))
   }
}
                    

// @LINE:24
case controllers_AbsenceController_findAll13(params) => {
   call { 
        invokeHandler(_root_.controllers.AbsenceController.findAll, HandlerDef(this, "controllers.AbsenceController", "findAll", Nil))
   }
}
                    

// @LINE:25
case controllers_AbsenceController_findInSprint14(params) => {
   call(params.fromPath[Long]("sprintId", None)) { (sprintId) =>
        invokeHandler(_root_.controllers.AbsenceController.findInSprint(sprintId), HandlerDef(this, "controllers.AbsenceController", "findInSprint", Seq(classOf[Long])))
   }
}
                    

// @LINE:27
case controllers_SprintController_create15(params) => {
   call { 
        invokeHandler(_root_.controllers.SprintController.create, HandlerDef(this, "controllers.SprintController", "create", Nil))
   }
}
                    

// @LINE:28
case controllers_SprintController_read16(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(_root_.controllers.SprintController.read(id), HandlerDef(this, "controllers.SprintController", "read", Seq(classOf[Long])))
   }
}
                    

// @LINE:29
case controllers_SprintController_update17(params) => {
   call { 
        invokeHandler(_root_.controllers.SprintController.update, HandlerDef(this, "controllers.SprintController", "update", Nil))
   }
}
                    

// @LINE:30
case controllers_SprintController_delete18(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(_root_.controllers.SprintController.delete(id), HandlerDef(this, "controllers.SprintController", "delete", Seq(classOf[Long])))
   }
}
                    

// @LINE:31
case controllers_SprintController_findCurrent19(params) => {
   call { 
        invokeHandler(_root_.controllers.SprintController.findCurrent, HandlerDef(this, "controllers.SprintController", "findCurrent", Nil))
   }
}
                    

// @LINE:33
case controllers_ViewController_getCurrentWeek20(params) => {
   call { 
        invokeHandler(_root_.controllers.ViewController.getCurrentWeek, HandlerDef(this, "controllers.ViewController", "getCurrentWeek", Nil))
   }
}
                    

// @LINE:35
case controllers_Main_scroll21(params) => {
   call { 
        invokeHandler(_root_.controllers.Main.scroll, HandlerDef(this, "controllers.Main", "scroll", Nil))
   }
}
                    

// @LINE:38
case controllers_Assets_at22(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(_root_.controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String])))
   }
}
                    
}
    
}
                