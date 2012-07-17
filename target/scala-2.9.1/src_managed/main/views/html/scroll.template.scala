
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
object scroll extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.Html] {

    /**/
    def apply():play.api.templates.Html = {
        _display_ {

Seq[Any](_display_(Seq[Any](/*1.2*/main("Scroll")/*1.16*/ {_display_(Seq[Any](format.raw/*1.18*/("""
<style type="text/css">
	#aCalendar
	"""),format.raw("""{"""),format.raw/*4.3*/("""
		width:100%;
		height: 30px;
		position: relative;
	"""),format.raw("""}"""),format.raw/*8.3*/("""
	
	#aCalendar div.scrollableArea img
	"""),format.raw("""{"""),format.raw/*11.3*/("""
		position: relative;
		float: left;
		margin: 0;
		padding: 0;
		/* If you don't want the images in the scroller to be selectable, try the following
		   block of code. It's just a nice feature that prevent the images from
		   accidentally becoming selected/inverted when the user interacts with the scroller. */
		-webkit-user-select: none;
		-khtml-user-select: none;
		-moz-user-select: none;
		-o-user-select: none;
		user-select: none;
	"""),format.raw("""}"""),format.raw/*24.3*/("""
	#makeMeScrollable
	"""),format.raw("""{"""),format.raw/*26.3*/("""
		width:100%;
		height: 330px;
		position: relative;
	"""),format.raw("""}"""),format.raw/*30.3*/("""
	
	#makeMeScrollable div.scrollableArea img
	"""),format.raw("""{"""),format.raw/*33.3*/("""
		position: relative;
		float: left;
		margin: 0;
		padding: 0;
		/* If you don't want the images in the scroller to be selectable, try the following
		   block of code. It's just a nice feature that prevent the images from
		   accidentally becoming selected/inverted when the user interacts with the scroller. */
		-webkit-user-select: none;
		-khtml-user-select: none;
		-moz-user-select: none;
		-o-user-select: none;
		user-select: none;
	"""),format.raw("""}"""),format.raw/*46.3*/("""
</style>
<script type="text/javascript" charset="utf-8">
$(function() """),format.raw("""{"""),format.raw/*49.15*/("""
	$("div#aCalendar").smoothDivScroll("""),format.raw("""{"""),format.raw("""}"""),format.raw/*50.39*/(""");
	$("div#makeMeScrollable").smoothDivScroll("""),format.raw("""{"""),format.raw("""}"""),format.raw/*51.46*/(""");
"""),format.raw("""}"""),format.raw/*52.2*/(""");
</script>

<div id="app">
<div id="users">
<div id="c">
Date
</div>
<div id="u1">
Dallas
</div>
<div id="u2">
Dolores
</div>
</div>
<div id="aCalendar">
<div id="row1"><div class="korv">Korv1</div></div>
<div id="row2"><div class="korv">Korv2</div></div>
<div id="row3"><div class="korv">Korv3</div></div>
</div>

<div id="makeMeScrollable">
	<img src="assets/images/smoothDivScroll/demo/field.jpg" alt="Demo image" id="field" />
	<img src="assets/images/smoothDivScroll/demo/gnome.jpg" alt="Demo image" id="gnome" />
	<img src="assets/images/smoothDivScroll/demo/pencils.jpg" alt="Demo image" id="pencils" />
	<img src="assets/images/smoothDivScroll/demo/golf.jpg" alt="Demo image" id="golf" />
	<img src="assets/images/smoothDivScroll/demo/river.jpg" alt="Demo image" id="river" />
	<img src="assets/images/smoothDivScroll/demo/train.jpg" alt="Demo image" id="train" />
	<img src="assets/images/smoothDivScroll/demo/leaf.jpg" alt="Demo image" id="leaf" />
	<img src="assets/images/smoothDivScroll/demo/dog.jpg" alt="Demo image" id="dog" />
</div>

</div>
""")))})))}
    }
    
    def render() = apply()
    
    def f:(() => play.api.templates.Html) = () => apply()
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jul 17 23:35:49 CEST 2012
                    SOURCE: /Users/magnus/Projects/playdo/absence/app/views/scroll.scala.html
                    HASH: 56d6fa7988b9ecb7a4826972477bb264bbe802df
                    MATRIX: 579->1|601->15|640->17|724->56|824->111|910->151|1402->597|1470->619|1572->675|1665->722|2157->1168|2276->1240|2381->1279|2495->1327|2545->1331
                    LINES: 22->1|22->1|22->1|25->4|29->8|32->11|45->24|47->26|51->30|54->33|67->46|70->49|71->50|72->51|73->52
                    -- GENERATED --
                */
            