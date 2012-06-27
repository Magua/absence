
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

Seq[Any](format.raw/*1.32*/("""

<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(Seq[Any](/*7.17*/title)),format.raw/*7.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*8.54*/routes/*8.60*/.Assets.at("stylesheets/main.css"))),format.raw/*8.94*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*9.54*/routes/*9.60*/.Assets.at("stylesheets/theme.css"))),format.raw/*9.95*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*10.54*/routes/*10.60*/.Assets.at("stylesheets/jquery-ui-1.8.20.custom.css"))),format.raw/*10.113*/("""">
        <link rel='stylesheet' type='text/css'  href=""""),_display_(Seq[Any](/*11.56*/routes/*11.62*/.Assets.at("stylesheets/fullcalendar.css"))),format.raw/*11.104*/("""">        
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq[Any](/*12.59*/routes/*12.65*/.Assets.at("images/favicon.png"))),format.raw/*12.97*/("""">
        <script src=""""),_display_(Seq[Any](/*13.23*/routes/*13.29*/.Assets.at("javascripts/json2.js"))),format.raw/*13.63*/("""" type="text/javascript"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*16.23*/routes/*16.29*/.Assets.at("javascripts/underscore.js"))),format.raw/*16.68*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*17.23*/routes/*17.29*/.Assets.at("javascripts/backbone.js"))),format.raw/*17.66*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*18.23*/routes/*18.29*/.Assets.at("javascripts/date.js"))),format.raw/*18.62*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*19.23*/routes/*19.29*/.Assets.at("javascripts/absenceBackbone.js"))),format.raw/*19.73*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*20.23*/routes/*20.29*/.Assets.at("javascripts/fullcalendar.js"))),format.raw/*20.70*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*21.23*/routes/*21.29*/.Assets.at("javascripts/gcal.js"))),format.raw/*21.62*/("""" type="text/javascript"></script>
    </head>
    <body>
        """),_display_(Seq[Any](/*24.10*/content)),format.raw/*24.17*/("""
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
                    DATE: Wed Jun 27 17:10:48 CEST 2012
                    SOURCE: /Users/magnus/Projects/git/absence/app/views/main.scala.html
                    HASH: 3608a30bad0dac34931021959e5fb526cdd360bc
                    MATRIX: 509->1|616->31|704->84|730->89|827->151|841->157|896->191|987->247|1001->253|1057->288|1149->344|1164->350|1240->403|1334->461|1349->467|1414->509|1519->578|1534->584|1588->616|1649->641|1664->647|1720->681|2047->972|2062->978|2123->1017|2216->1074|2231->1080|2290->1117|2383->1174|2398->1180|2453->1213|2546->1270|2561->1276|2627->1320|2720->1377|2735->1383|2798->1424|2891->1481|2906->1487|2961->1520|3064->1587|3093->1594
                    LINES: 19->1|22->1|28->7|28->7|29->8|29->8|29->8|30->9|30->9|30->9|31->10|31->10|31->10|32->11|32->11|32->11|33->12|33->12|33->12|34->13|34->13|34->13|37->16|37->16|37->16|38->17|38->17|38->17|39->18|39->18|39->18|40->19|40->19|40->19|41->20|41->20|41->20|42->21|42->21|42->21|45->24|45->24
                    -- GENERATED --
                */
            