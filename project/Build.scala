import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "absence"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
    		"net.liftweb" % "lift-json_2.9.1" % "2.4",
    		"se.radley" %% "play-plugins-salat" % "1.0.7"
	)

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
    	routesImport += "se.radley.plugin.salat.Binders._",
  		templatesImport += "org.bson.types.ObjectId"
    )
    

}
