// @SOURCE:/Users/magnus/Projects/playdo/absence/conf/routes
// @HASH:eb4b6d6ae876c90e93d227d95d81c9d5b610bb5e
// @DATE:Tue Jul 17 23:35:46 CEST 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString


// @LINE:40
// @LINE:37
// @LINE:35
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:13
// @LINE:11
// @LINE:9
// @LINE:6
package controllers {

// @LINE:40
class ReverseAssets {
    


 
// @LINE:40
def at(file:String) = {
   Call("GET", "/assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                        

                      
    
}
                            

// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
class ReverseAbsenceController {
    


 
// @LINE:24
def update() = {
   Call("PUT", "/absence")
}
                                                        
 
// @LINE:23
def read(id:Long) = {
   Call("GET", "/absence/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        
 
// @LINE:25
def delete(id:Long) = {
   Call("DELETE", "/absence/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        
 
// @LINE:26
def findAll() = {
   Call("GET", "/absence")
}
                                                        
 
// @LINE:22
def create() = {
   Call("POST", "/absence")
}
                                                        
 
// @LINE:27
def findInSprint(sprintId:Long) = {
   Call("GET", "/absenceInSprint/" + implicitly[PathBindable[Long]].unbind("sprintId", sprintId))
}
                                                        

                      
    
}
                            

// @LINE:35
class ReverseViewController {
    


 
// @LINE:35
def getCurrentWeek() = {
   Call("GET", "/view/current")
}
                                                        

                      
    
}
                            

// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
class ReverseSprintController {
    


 
// @LINE:31
def update() = {
   Call("PUT", "/sprint")
}
                                                        
 
// @LINE:30
def read(id:Long) = {
   Call("GET", "/sprint/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        
 
// @LINE:33
def findCurrent() = {
   Call("GET", "/sprintCurrent")
}
                                                        
 
// @LINE:32
def delete(id:Long) = {
   Call("DELETE", "/sprint/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        
 
// @LINE:29
def create() = {
   Call("POST", "/sprint")
}
                                                        

                      
    
}
                            

// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
class ReverseUserController {
    


 
// @LINE:18
def update() = {
   Call("PUT", "/user")
}
                                                        
 
// @LINE:17
def read(id:Long) = {
   Call("GET", "/user/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        
 
// @LINE:19
def delete(id:Long) = {
   Call("DELETE", "/user/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        
 
// @LINE:20
def findAll() = {
   Call("GET", "/user")
}
                                                        
 
// @LINE:16
def create() = {
   Call("POST", "/user")
}
                                                        

                      
    
}
                            

// @LINE:37
// @LINE:13
// @LINE:11
// @LINE:9
// @LINE:6
class ReverseMain {
    


 
// @LINE:11
def comet(sessionId:String) = {
   Call("GET", "/comet/" + implicitly[PathBindable[String]].unbind("sessionId", sessionId))
}
                                                        
 
// @LINE:37
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
                                                        
 
// @LINE:13
def serverSentEvents(sessionId:String) = {
   Call("GET", "/serverSentEvents/" + implicitly[PathBindable[String]].unbind("sessionId", sessionId))
}
                                                        

                      
    
}
                            
}
                    


// @LINE:40
// @LINE:37
// @LINE:35
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:13
// @LINE:11
// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:40
class ReverseAssets {
    


 
// @LINE:40
def at = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"/assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
class ReverseAbsenceController {
    


 
// @LINE:24
def update = JavascriptReverseRoute(
   "controllers.AbsenceController.update",
   """
      function() {
      return _wA({method:"PUT", url:"/absence"})
      }
   """
)
                                                        
 
// @LINE:23
def read = JavascriptReverseRoute(
   "controllers.AbsenceController.read",
   """
      function(id) {
      return _wA({method:"GET", url:"/absence/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        
 
// @LINE:25
def delete = JavascriptReverseRoute(
   "controllers.AbsenceController.delete",
   """
      function(id) {
      return _wA({method:"DELETE", url:"/absence/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        
 
// @LINE:26
def findAll = JavascriptReverseRoute(
   "controllers.AbsenceController.findAll",
   """
      function() {
      return _wA({method:"GET", url:"/absence"})
      }
   """
)
                                                        
 
// @LINE:22
def create = JavascriptReverseRoute(
   "controllers.AbsenceController.create",
   """
      function() {
      return _wA({method:"POST", url:"/absence"})
      }
   """
)
                                                        
 
// @LINE:27
def findInSprint = JavascriptReverseRoute(
   "controllers.AbsenceController.findInSprint",
   """
      function(sprintId) {
      return _wA({method:"GET", url:"/absenceInSprint/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("sprintId", sprintId)})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:35
class ReverseViewController {
    


 
// @LINE:35
def getCurrentWeek = JavascriptReverseRoute(
   "controllers.ViewController.getCurrentWeek",
   """
      function() {
      return _wA({method:"GET", url:"/view/current"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
class ReverseSprintController {
    


 
// @LINE:31
def update = JavascriptReverseRoute(
   "controllers.SprintController.update",
   """
      function() {
      return _wA({method:"PUT", url:"/sprint"})
      }
   """
)
                                                        
 
// @LINE:30
def read = JavascriptReverseRoute(
   "controllers.SprintController.read",
   """
      function(id) {
      return _wA({method:"GET", url:"/sprint/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        
 
// @LINE:33
def findCurrent = JavascriptReverseRoute(
   "controllers.SprintController.findCurrent",
   """
      function() {
      return _wA({method:"GET", url:"/sprintCurrent"})
      }
   """
)
                                                        
 
// @LINE:32
def delete = JavascriptReverseRoute(
   "controllers.SprintController.delete",
   """
      function(id) {
      return _wA({method:"DELETE", url:"/sprint/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        
 
// @LINE:29
def create = JavascriptReverseRoute(
   "controllers.SprintController.create",
   """
      function() {
      return _wA({method:"POST", url:"/sprint"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
class ReverseUserController {
    


 
// @LINE:18
def update = JavascriptReverseRoute(
   "controllers.UserController.update",
   """
      function() {
      return _wA({method:"PUT", url:"/user"})
      }
   """
)
                                                        
 
// @LINE:17
def read = JavascriptReverseRoute(
   "controllers.UserController.read",
   """
      function(id) {
      return _wA({method:"GET", url:"/user/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        
 
// @LINE:19
def delete = JavascriptReverseRoute(
   "controllers.UserController.delete",
   """
      function(id) {
      return _wA({method:"DELETE", url:"/user/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        
 
// @LINE:20
def findAll = JavascriptReverseRoute(
   "controllers.UserController.findAll",
   """
      function() {
      return _wA({method:"GET", url:"/user"})
      }
   """
)
                                                        
 
// @LINE:16
def create = JavascriptReverseRoute(
   "controllers.UserController.create",
   """
      function() {
      return _wA({method:"POST", url:"/user"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:37
// @LINE:13
// @LINE:11
// @LINE:9
// @LINE:6
class ReverseMain {
    


 
// @LINE:11
def comet = JavascriptReverseRoute(
   "controllers.Main.comet",
   """
      function(sessionId) {
      return _wA({method:"GET", url:"/comet/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("sessionId", sessionId)})
      }
   """
)
                                                        
 
// @LINE:37
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
                                                        
 
// @LINE:13
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
                    


// @LINE:40
// @LINE:37
// @LINE:35
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:13
// @LINE:11
// @LINE:9
// @LINE:6
package controllers.ref {

// @LINE:40
class ReverseAssets {
    


 
// @LINE:40
def at(path:String, file:String) = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]))
)
                              

                      
    
}
                            

// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
class ReverseAbsenceController {
    


 
// @LINE:24
def update() = new play.api.mvc.HandlerRef(
   controllers.AbsenceController.update(), HandlerDef(this, "controllers.AbsenceController", "update", Seq())
)
                              
 
// @LINE:23
def read(id:Long) = new play.api.mvc.HandlerRef(
   controllers.AbsenceController.read(id), HandlerDef(this, "controllers.AbsenceController", "read", Seq(classOf[Long]))
)
                              
 
// @LINE:25
def delete(id:Long) = new play.api.mvc.HandlerRef(
   controllers.AbsenceController.delete(id), HandlerDef(this, "controllers.AbsenceController", "delete", Seq(classOf[Long]))
)
                              
 
// @LINE:26
def findAll() = new play.api.mvc.HandlerRef(
   controllers.AbsenceController.findAll(), HandlerDef(this, "controllers.AbsenceController", "findAll", Seq())
)
                              
 
// @LINE:22
def create() = new play.api.mvc.HandlerRef(
   controllers.AbsenceController.create(), HandlerDef(this, "controllers.AbsenceController", "create", Seq())
)
                              
 
// @LINE:27
def findInSprint(sprintId:Long) = new play.api.mvc.HandlerRef(
   controllers.AbsenceController.findInSprint(sprintId), HandlerDef(this, "controllers.AbsenceController", "findInSprint", Seq(classOf[Long]))
)
                              

                      
    
}
                            

// @LINE:35
class ReverseViewController {
    


 
// @LINE:35
def getCurrentWeek() = new play.api.mvc.HandlerRef(
   controllers.ViewController.getCurrentWeek(), HandlerDef(this, "controllers.ViewController", "getCurrentWeek", Seq())
)
                              

                      
    
}
                            

// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
class ReverseSprintController {
    


 
// @LINE:31
def update() = new play.api.mvc.HandlerRef(
   controllers.SprintController.update(), HandlerDef(this, "controllers.SprintController", "update", Seq())
)
                              
 
// @LINE:30
def read(id:Long) = new play.api.mvc.HandlerRef(
   controllers.SprintController.read(id), HandlerDef(this, "controllers.SprintController", "read", Seq(classOf[Long]))
)
                              
 
// @LINE:33
def findCurrent() = new play.api.mvc.HandlerRef(
   controllers.SprintController.findCurrent(), HandlerDef(this, "controllers.SprintController", "findCurrent", Seq())
)
                              
 
// @LINE:32
def delete(id:Long) = new play.api.mvc.HandlerRef(
   controllers.SprintController.delete(id), HandlerDef(this, "controllers.SprintController", "delete", Seq(classOf[Long]))
)
                              
 
// @LINE:29
def create() = new play.api.mvc.HandlerRef(
   controllers.SprintController.create(), HandlerDef(this, "controllers.SprintController", "create", Seq())
)
                              

                      
    
}
                            

// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
class ReverseUserController {
    


 
// @LINE:18
def update() = new play.api.mvc.HandlerRef(
   controllers.UserController.update(), HandlerDef(this, "controllers.UserController", "update", Seq())
)
                              
 
// @LINE:17
def read(id:Long) = new play.api.mvc.HandlerRef(
   controllers.UserController.read(id), HandlerDef(this, "controllers.UserController", "read", Seq(classOf[Long]))
)
                              
 
// @LINE:19
def delete(id:Long) = new play.api.mvc.HandlerRef(
   controllers.UserController.delete(id), HandlerDef(this, "controllers.UserController", "delete", Seq(classOf[Long]))
)
                              
 
// @LINE:20
def findAll() = new play.api.mvc.HandlerRef(
   controllers.UserController.findAll(), HandlerDef(this, "controllers.UserController", "findAll", Seq())
)
                              
 
// @LINE:16
def create() = new play.api.mvc.HandlerRef(
   controllers.UserController.create(), HandlerDef(this, "controllers.UserController", "create", Seq())
)
                              

                      
    
}
                            

// @LINE:37
// @LINE:13
// @LINE:11
// @LINE:9
// @LINE:6
class ReverseMain {
    


 
// @LINE:11
def comet(sessionId:String) = new play.api.mvc.HandlerRef(
   controllers.Main.comet(sessionId), HandlerDef(this, "controllers.Main", "comet", Seq(classOf[String]))
)
                              
 
// @LINE:37
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
                              
 
// @LINE:13
def serverSentEvents(sessionId:String) = new play.api.mvc.HandlerRef(
   controllers.Main.serverSentEvents(sessionId), HandlerDef(this, "controllers.Main", "serverSentEvents", Seq(classOf[String]))
)
                              

                      
    
}
                            
}
                    
                