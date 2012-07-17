
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
object absence extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[String,String,String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(webSocketPort: String, sessionId: String, users: String, absences: String):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.77*/("""
"""),_display_(Seq[Any](/*2.2*/main("Absence")/*2.17*/ {_display_(Seq[Any](format.raw/*2.19*/("""
<script type="text/javascript" charset="utf-8">
$(function() """),format.raw("""{"""),format.raw/*4.15*/("""
	absenceNS.init('"""),_display_(Seq[Any](/*5.19*/sessionId)),format.raw/*5.28*/("""')
	absenceNS.users.add("""),_display_(Seq[Any](/*6.23*/Html(users))),format.raw/*6.34*/(""")
	absenceNS.absences.add("""),_display_(Seq[Any](/*7.26*/Html(absences))),format.raw/*7.40*/(""")
"""),format.raw("""}"""),format.raw/*8.2*/(""")
</script>
<div id='eventDialog' class='dialog ui-helper-hidden'>
<form>
    <div>
        <label>Title:</label>
        <input id='title' class="field" type="text"></input>
    </div>
    <div>
        <label>userId:</label>
        <input id='userId' class="field" type="text"></input>
    </div>
    <div>
        <label>All day (TODO):</label>
        <input id='allDay' class="field" type="checkbox"></input>
    </div>
</form>
</div>
<div id="calendar"></div>
<div id="log"></div>
""")))})))}
    }
    
    def render(webSocketPort:String,sessionId:String,users:String,absences:String) = apply(webSocketPort,sessionId,users,absences)
    
    def f:((String,String,String,String) => play.api.templates.Html) = (webSocketPort,sessionId,users,absences) => apply(webSocketPort,sessionId,users,absences)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jul 17 23:35:48 CEST 2012
                    SOURCE: /Users/magnus/Projects/playdo/absence/app/views/absence.scala.html
                    HASH: 40bac383814945d0b27c3e5faeb6f0fb9ddf354c
                    MATRIX: 528->1|680->76|716->78|739->93|778->95|887->158|941->177|971->186|1031->211|1063->222|1125->249|1160->263|1208->266
                    LINES: 19->1|22->1|23->2|23->2|23->2|25->4|26->5|26->5|27->6|27->6|28->7|28->7|29->8
                    -- GENERATED --
                */
            