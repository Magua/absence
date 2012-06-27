
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
object main extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(title: String)(content: Html):play.api.templates.Html = {
        _display_ {

Seq(format.raw/*1.32*/("""

<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(Seq(/*7.17*/title)),format.raw/*7.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq(/*8.54*/routes/*8.60*/.Assets.at("stylesheets/main.css"))),format.raw/*8.94*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq(/*9.54*/routes/*9.60*/.Assets.at("stylesheets/theme.css"))),format.raw/*9.95*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq(/*10.54*/routes/*10.60*/.Assets.at("stylesheets/jquery-ui-1.8.20.custom.css"))),format.raw/*10.113*/("""">
        <link rel='stylesheet' type='text/css'  href=""""),_display_(Seq(/*11.56*/routes/*11.62*/.Assets.at("stylesheets/fullcalendar.css"))),format.raw/*11.104*/("""">        
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq(/*12.59*/routes/*12.65*/.Assets.at("images/favicon.png"))),format.raw/*12.97*/("""">
        <script src=""""),_display_(Seq(/*13.23*/routes/*13.29*/.Assets.at("javascripts/json2.js"))),format.raw/*13.63*/("""" type="text/javascript"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js" type="text/javascript"></script>
        <script src=""""),_display_(Seq(/*16.23*/routes/*16.29*/.Assets.at("javascripts/underscore.js"))),format.raw/*16.68*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq(/*17.23*/routes/*17.29*/.Assets.at("javascripts/backbone.js"))),format.raw/*17.66*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq(/*18.23*/routes/*18.29*/.Assets.at("javascripts/date.js"))),format.raw/*18.62*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq(/*19.23*/routes/*19.29*/.Assets.at("javascripts/absenceBackbone.js"))),format.raw/*19.73*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq(/*20.23*/routes/*20.29*/.Assets.at("javascripts/fullcalendar.js"))),format.raw/*20.70*/("""" type="text/javascript"></script>
    </head>
    <body>
        """),_display_(Seq(/*23.10*/content)),format.raw/*23.17*/("""
    </body>
</html>
"""))}
    }
    
    def render(title:String,content:Html) = apply(title)(content)
    
    def f:((String) => (Html) => play.api.templates.Html) = (title) => (content) => apply(title)(content)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Fri May 18 00:32:16 CEST 2012
                    SOURCE: /Users/magnus/Projects/git/absence/app/views/main.scala.html
                    HASH: 6fe7c20a288b3d3dbfbae1a9bc82bcc183c8fc94
                    MATRIX: 509->1|611->31|694->84|720->89|812->151|826->157|881->191|967->247|981->253|1037->288|1124->344|1139->350|1215->403|1304->461|1319->467|1384->509|1484->578|1499->584|1553->616|1609->641|1624->647|1680->681|2002->972|2017->978|2078->1017|2166->1074|2181->1080|2240->1117|2328->1174|2343->1180|2398->1213|2486->1270|2501->1276|2567->1320|2655->1377|2670->1383|2733->1424|2831->1491|2860->1498
                    LINES: 19->1|22->1|28->7|28->7|29->8|29->8|29->8|30->9|30->9|30->9|31->10|31->10|31->10|32->11|32->11|32->11|33->12|33->12|33->12|34->13|34->13|34->13|37->16|37->16|37->16|38->17|38->17|38->17|39->18|39->18|39->18|40->19|40->19|40->19|41->20|41->20|41->20|44->23|44->23
                    -- GENERATED --
                */
            