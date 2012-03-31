import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "absence"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "net.liftweb" % "lift-json_2.9.1" % "2.4"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here      
    )

}
