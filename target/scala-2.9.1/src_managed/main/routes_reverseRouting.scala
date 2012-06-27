// @SOURCE:/Users/magnus/Projects/git/absence/conf/routes
// @HASH:fe698f2315e86fb85d47aaccbae3561eef5a5d6d
// @DATE:Thu May 10 21:50:19 CEST 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString


// @LINE:38
// @LINE:35
// @LINE:33
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers {

// @LINE:38
class ReverseAssets {
    


 
// @LINE:38
def at(file:String) = {
   Call("GET", "/assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                        

                      
    
}
                            

// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
class ReverseAbsenceController {
    


 
// @LINE:22
def update() = {
   Call("PUT", "/absence")
}
                                                        
 
// @LINE:21
def read(id:Long) = {
   Call("GET", "/absence/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        
 
// @LINE:23
def delete(id:Long) = {
   Call("DELETE", "/absence/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        
 
// @LINE:24
def findAll() = {
   Call("GET", "/absence")
}
                                                        
 
// @LINE:20
def create() = {
   Call("POST", "/absence")
}
                                                        
 
// @LINE:25
def findInSprint(sprintId:Long) = {
   Call("GET", "/absenceInSprint/" + implicitly[PathBindable[Long]].unbind("sprintId", sprintId))
}
                                                        

                      
    
}
                            

// @LINE:33
class ReverseViewController {
    


 
// @LINE:33
def getCurrentWeek() = {
   Call("GET", "/view/current")
}
                                                        

                      
    
}
                            

// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
class ReverseSprintController {
    


 
// @LINE:29
def update() = {
   Call("PUT", "/sprint")
}
                                                        
 
// @LINE:28
def read(id:Long) = {
   Call("GET", "/sprint/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        
 
// @LINE:31
def findCurrent() = {
   Call("GET", "/sprintCurrent")
}
                                                        
 
// @LINE:30
def delete(id:Long) = {
   Call("DELETE", "/sprint/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        
 
// @LINE:27
def create() = {
   Call("POST", "/sprint")
}
                                                        

                      
    
}
                            

// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
class ReverseUserController {
    


 
// @LINE:16
def update() = {
   Call("PUT", "/user")
}
                                                        
 
// @LINE:15
def read(id:Long) = {
   Call("GET", "/user/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        
 
// @LINE:17
def delete(id:Long) = {
   Call("DELETE", "/user/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        
 
// @LINE:18
def findAll() = {
   Call("GET", "/user")
}
                                                        
 
// @LINE:14
def create() = {
   Call("POST", "/user")
}
                                                        

                      
    
}
                            

// @LINE:35
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseMain {
    


 
// @LINE:10
def comet(sessionId:String) = {
   Call("GET", "/comet/" + implicitly[PathBindable[String]].unbind("sessionId", sessionId))
}
                                                        
 
// @LINE:35
def scroll() = {
   Call("GET", "/scroll")
}
                                                        
 
// @LINE:6
def absence() = {
   Call("GET", "/")
}
                                                        
 
// @LINE:9
def connect(sessionId:String) = {
   Call("GET", "/connect/" + implicitly[PathBindable[String]].unbind("sessionId", sessionId))
}
                                                        
 
// @LINE:11
def serverSentEvents(sessionId:String) = {
   Call("GET", "/serverSentEvents/" + implicitly[PathBindable[String]].unbind("sessionId", sessionId))
}
                                                        

                      
    
}
                            
}
                    


// @LINE:38
// @LINE:35
// @LINE:33
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:38
class ReverseAssets {
    


 
// @LINE:38
def at = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"/assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
class ReverseAbsenceController {
    


 
// @LINE:22
def update = JavascriptReverseRoute(
   "controllers.AbsenceController.update",
   """
      function() {
      return _wA({method:"PUT", url:"/absence"})
      }
   """
)
                                                        
 
// @LINE:21
def read = JavascriptReverseRoute(
   "controllers.AbsenceController.read",
   """
      function(id) {
      return _wA({method:"GET", url:"/absence/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        
 
// @LINE:23
def delete = JavascriptReverseRoute(
   "controllers.AbsenceController.delete",
   """
      function(id) {
      return _wA({method:"DELETE", url:"/absence/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        
 
// @LINE:24
def findAll = JavascriptReverseRoute(
   "controllers.AbsenceController.findAll",
   """
      function() {
      return _wA({method:"GET", url:"/absence"})
      }
   """
)
                                                        
 
// @LINE:20
def create = JavascriptReverseRoute(
   "controllers.AbsenceController.create",
   """
      function() {
      return _wA({method:"POST", url:"/absence"})
      }
   """
)
                                                        
 
// @LINE:25
def findInSprint = JavascriptReverseRoute(
   "controllers.AbsenceController.findInSprint",
   """
      function(sprintId) {
      return _wA({method:"GET", url:"/absenceInSprint/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("sprintId", sprintId)})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:33
class ReverseViewController {
    


 
// @LINE:33
def getCurrentWeek = JavascriptReverseRoute(
   "controllers.ViewController.getCurrentWeek",
   """
      function() {
      return _wA({method:"GET", url:"/view/current"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
class ReverseSprintController {
    


 
// @LINE:29
def update = JavascriptReverseRoute(
   "controllers.SprintController.update",
   """
      function() {
      return _wA({method:"PUT", url:"/sprint"})
      }
   """
)
                                                        
 
// @LINE:28
def read = JavascriptReverseRoute(
   "controllers.SprintController.read",
   """
      function(id) {
      return _wA({method:"GET", url:"/sprint/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        
 
// @LINE:31
def findCurrent = JavascriptReverseRoute(
   "controllers.SprintController.findCurrent",
   """
      function() {
      return _wA({method:"GET", url:"/sprintCurrent"})
      }
   """
)
                                                        
 
// @LINE:30
def delete = JavascriptReverseRoute(
   "controllers.SprintController.delete",
   """
      function(id) {
      return _wA({method:"DELETE", url:"/sprint/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        
 
// @LINE:27
def create = JavascriptReverseRoute(
   "controllers.SprintController.create",
   """
      function() {
      return _wA({method:"POST", url:"/sprint"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
class ReverseUserController {
    


 
// @LINE:16
def update = JavascriptReverseRoute(
   "controllers.UserController.update",
   """
      function() {
      return _wA({method:"PUT", url:"/user"})
      }
   """
)
                                                        
 
// @LINE:15
def read = JavascriptReverseRoute(
   "controllers.UserController.read",
   """
      function(id) {
      return _wA({method:"GET", url:"/user/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        
 
// @LINE:17
def delete = JavascriptReverseRoute(
   "controllers.UserController.delete",
   """
      function(id) {
      return _wA({method:"DELETE", url:"/user/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        
 
// @LINE:18
def findAll = JavascriptReverseRoute(
   "controllers.UserController.findAll",
   """
      function() {
      return _wA({method:"GET", url:"/user"})
      }
   """
)
                                                        
 
// @LINE:14
def create = JavascriptReverseRoute(
   "controllers.UserController.create",
   """
      function() {
      return _wA({method:"POST", url:"/user"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:35
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseMain {
    


 
// @LINE:10
def comet = JavascriptReverseRoute(
   "controllers.Main.comet",
   """
      function(sessionId) {
      return _wA({method:"GET", url:"/comet/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("sessionId", sessionId)})
      }
   """
)
                                                        
 
// @LINE:35
def scroll = JavascriptReverseRoute(
   "controllers.Main.scroll",
   """
      function() {
      return _wA({method:"GET", url:"/scroll"})
      }
   """
)
                                                        
 
// @LINE:6
def absence = JavascriptReverseRoute(
   "controllers.Main.absence",
   """
      function() {
      return _wA({method:"GET", url:"/"})
      }
   """
)
                                                        
 
// @LINE:9
def connect = JavascriptReverseRoute(
   "controllers.Main.connect",
   """
      function(sessionId) {
      return _wA({method:"GET", url:"/connect/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("sessionId", sessionId)})
      }
   """
)
                                                        
 
// @LINE:11
def serverSentEvents = JavascriptReverseRoute(
   "controllers.Main.serverSentEvents",
   """
      function(sessionId) {
      return _wA({method:"GET", url:"/serverSentEvents/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("sessionId", sessionId)})
      }
   """
)
                                                        

                      
    
}
                            
}
                    


// @LINE:38
// @LINE:35
// @LINE:33
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.ref {

// @LINE:38
class ReverseAssets {
    


 
// @LINE:38
def at(path:String, file:String) = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]))
)
                              

                      
    
}
                            

// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
class ReverseAbsenceController {
    


 
// @LINE:22
def update() = new play.api.mvc.HandlerRef(
   controllers.AbsenceController.update(), HandlerDef(this, "controllers.AbsenceController", "update", Seq())
)
                              
 
// @LINE:21
def read(id:Long) = new play.api.mvc.HandlerRef(
   controllers.AbsenceController.read(id), HandlerDef(this, "controllers.AbsenceController", "read", Seq(classOf[Long]))
)
                              
 
// @LINE:23
def delete(id:Long) = new play.api.mvc.HandlerRef(
   controllers.AbsenceController.delete(id), HandlerDef(this, "controllers.AbsenceController", "delete", Seq(classOf[Long]))
)
                              
 
// @LINE:24
def findAll() = new play.api.mvc.HandlerRef(
   controllers.AbsenceController.findAll(), HandlerDef(this, "controllers.AbsenceController", "findAll", Seq())
)
                              
 
// @LINE:20
def create() = new play.api.mvc.HandlerRef(
   controllers.AbsenceController.create(), HandlerDef(this, "controllers.AbsenceController", "create", Seq())
)
                              
 
// @LINE:25
def findInSprint(sprintId:Long) = new play.api.mvc.HandlerRef(
   controllers.AbsenceController.findInSprint(sprintId), HandlerDef(this, "controllers.AbsenceController", "findInSprint", Seq(classOf[Long]))
)
                              

                      
    
}
                            

// @LINE:33
class ReverseViewController {
    


 
// @LINE:33
def getCurrentWeek() = new play.api.mvc.HandlerRef(
   controllers.ViewController.getCurrentWeek(), HandlerDef(this, "controllers.ViewController", "getCurrentWeek", Seq())
)
                              

                      
    
}
                            

// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
class ReverseSprintController {
    


 
// @LINE:29
def update() = new play.api.mvc.HandlerRef(
   controllers.SprintController.update(), HandlerDef(this, "controllers.SprintController", "update", Seq())
)
                              
 
// @LINE:28
def read(id:Long) = new play.api.mvc.HandlerRef(
   controllers.SprintController.read(id), HandlerDef(this, "controllers.SprintController", "read", Seq(classOf[Long]))
)
                              
 
// @LINE:31
def findCurrent() = new play.api.mvc.HandlerRef(
   controllers.SprintController.findCurrent(), HandlerDef(this, "controllers.SprintController", "findCurrent", Seq())
)
                              
 
// @LINE:30
def delete(id:Long) = new play.api.mvc.HandlerRef(
   controllers.SprintController.delete(id), HandlerDef(this, "controllers.SprintController", "delete", Seq(classOf[Long]))
)
                              
 
// @LINE:27
def create() = new play.api.mvc.HandlerRef(
   controllers.SprintController.create(), HandlerDef(this, "controllers.SprintController", "create", Seq())
)
                              

                      
    
}
                            

// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
class ReverseUserController {
    


 
// @LINE:16
def update() = new play.api.mvc.HandlerRef(
   controllers.UserController.update(), HandlerDef(this, "controllers.UserController", "update", Seq())
)
                              
 
// @LINE:15
def read(id:Long) = new play.api.mvc.HandlerRef(
   controllers.UserController.read(id), HandlerDef(this, "controllers.UserController", "read", Seq(classOf[Long]))
)
                              
 
// @LINE:17
def delete(id:Long) = new play.api.mvc.HandlerRef(
   controllers.UserController.delete(id), HandlerDef(this, "controllers.UserController", "delete", Seq(classOf[Long]))
)
                              
 
// @LINE:18
def findAll() = new play.api.mvc.HandlerRef(
   controllers.UserController.findAll(), HandlerDef(this, "controllers.UserController", "findAll", Seq())
)
                              
 
// @LINE:14
def create() = new play.api.mvc.HandlerRef(
   controllers.UserController.create(), HandlerDef(this, "controllers.UserController", "create", Seq())
)
                              

                      
    
}
                            

// @LINE:35
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseMain {
    


 
// @LINE:10
def comet(sessionId:String) = new play.api.mvc.HandlerRef(
   controllers.Main.comet(sessionId), HandlerDef(this, "controllers.Main", "comet", Seq(classOf[String]))
)
                              
 
// @LINE:35
def scroll() = new play.api.mvc.HandlerRef(
   controllers.Main.scroll(), HandlerDef(this, "controllers.Main", "scroll", Seq())
)
                              
 
// @LINE:6
def absence() = new play.api.mvc.HandlerRef(
   controllers.Main.absence(), HandlerDef(this, "controllers.Main", "absence", Seq())
)
                              
 
// @LINE:9
def connect(sessionId:String) = new play.api.mvc.HandlerRef(
   controllers.Main.connect(sessionId), HandlerDef(this, "controllers.Main", "connect", Seq(classOf[String]))
)
                              
 
// @LINE:11
def serverSentEvents(sessionId:String) = new play.api.mvc.HandlerRef(
   controllers.Main.serverSentEvents(sessionId), HandlerDef(this, "controllers.Main", "serverSentEvents", Seq(classOf[String]))
)
                              

                      
    
}
                            
}
                    
                