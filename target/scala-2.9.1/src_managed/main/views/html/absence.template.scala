
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

Seq(format.raw/*1.77*/("""
"""),_display_(Seq(/*2.2*/main("Absence")/*2.17*/ {_display_(Seq(format.raw/*2.19*/("""
<script type="text/javascript" charset="utf-8">
$(function() """),format.raw("""{"""),format.raw/*4.15*/("""
	absenceNS.init('"""),_display_(Seq(/*5.19*/sessionId)),format.raw/*5.28*/("""')
	absenceNS.users.add("""),_display_(Seq(/*6.23*/Html(users))),format.raw/*6.34*/(""")
	absenceNS.absences.add("""),_display_(Seq(/*7.26*/Html(absences))),format.raw/*7.40*/(""")
"""),format.raw("""}"""),format.raw/*8.2*/(""")
</script>
<script type="text/template" id="user-template">
	<li>
		<span><%= id %> - </span>
		<span class="liUserName"><%= name %></span>
		<a class="liUserDelete" style="colour:red" href="#">X</a>
	</li>
</script>
<script type="text/template" id="absence-template">
	<li>
		<span><%= id %> - </span>
		<span class="liAbsenceDescription"><%= description %></span>
		<span class="liAbsenceStart"><%= new Date(start).toString("yyyy-MM-dd HH:mm") %></span> <->
		<span class="liAbsenceEnd"><%= new Date(end).toString("yyyy-MM-dd HH:mm") %></span>
		<a class="liAbsenceDelete" style="colour:red" href="#">X</a></li>
</script>
<div id='eventDialog' class='dialog ui-helper-hidden'>
            <form>
                <div>
                    <label>Title:</label>
                    <input id='title' class="field" type="text"></input>
                </div>
                <div>
                    <label>Color:</label>
                    <input id='color' class="field" type="text"></input>
                </div>
            </form>
</div>

	<div id="calendar"></div>
	<div>
    	<ul id="user-list"></ul>
		<ul id="absence-list"></ul>    	
    </div>
    <div id="newUserRequestDiv"  style=" border : 2px dotted red">
    	<div>Name: <input type="text" id="newUserRequestName"></div>
    	<button id="newUserRequestButton">create user</button>
    </div>
    <div id="allUsersRequestDiv"  style=" border : 2px dotted pink">
    	<button id="allUsersRequestButton">get all users</button>
    </div>
    <div id="newAbsenceRequestDiv"  style=" border : 2px dotted blue">
    	<div>UserId: <input type="text" id="newAbsenceRequestUserId" placeholder="a valid user id"></div>
    	<div>Description: <input type="text" id="newAbsenceRequestDescription" placeholder="description"></div>
    	<div>Start: <input type="text" name="newAbsenceRequestStart" id="newAbsenceRequestStart" value=""></div>
    	<div>End: <input type="text" name="newAbsenceRequestEnd" id="newAbsenceRequestEnd" value=""></div>
    	<button id="newAbsenceRequestButton">create absence</button>
    </div>
    <div id="allAbsenceRequestDiv"  style=" border : 2px dotted green">
    	<button id="allAbsenceRequestButton">get all absence</button>
    </div>
    <div id="currentWeekRequestDiv"  style=" border : 2px dotted purple">
      <button id="currentWeekRequestButton">get current week</button>
    </div>
    <div id="log"></div>
""")))})))}
    }
    
    def render(webSocketPort:String,sessionId:String,users:String,absences:String) = apply(webSocketPort,sessionId,users,absences)
    
    def f:((String,String,String,String) => play.api.templates.Html) = (webSocketPort,sessionId,users,absences) => apply(webSocketPort,sessionId,users,absences)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Thu May 17 23:16:53 CEST 2012
                    SOURCE: /Users/magnus/Projects/git/absence/app/views/absence.scala.html
                    HASH: ced1d04d57e7e85ce247e684b75ca3b89ed04366
                    MATRIX: 528->1|675->76|706->78|729->93|763->95|872->158|921->177|951->186|1006->211|1038->222|1095->249|1130->263|1178->266
                    LINES: 19->1|22->1|23->2|23->2|23->2|25->4|26->5|26->5|27->6|27->6|28->7|28->7|29->8
                    -- GENERATED --
                */
            