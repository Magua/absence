
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
/**/
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(message: String):play.api.templates.Html = {
        _display_ {

Seq(format.raw/*1.19*/("""

"""),_display_(Seq(/*3.2*/main("Welcome to Play 2.0")/*3.29*/ {_display_(Seq(format.raw/*3.31*/("""
    
    """),_display_(Seq(/*5.6*/play20/*5.12*/.welcome(message))),format.raw/*5.29*/("""
    
""")))})))}
    }
    
    def render(message:String) = apply(message)
    
    def f:((String) => play.api.templates.Html) = (message) => apply(message)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Thu May 10 21:50:19 CEST 2012
                    SOURCE: /Users/magnus/Projects/git/absence/app/views/index.scala.html
                    HASH: a9468e14516db88237e3431e8b54cf7780705202
                    MATRIX: 505->1|594->18|626->21|661->48|695->50|735->61|749->67|787->84
                    LINES: 19->1|22->1|24->3|24->3|24->3|26->5|26->5|26->5
                    -- GENERATED --
                */
            