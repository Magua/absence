package models
import java.util.Calendar
import java.text.SimpleDateFormat
import org.apache.commons.lang.time.DateUtils
import scala.collection.mutable.Buffer
import scala.collection.mutable.ArrayBuffer

case class View(days: List[Day], name: String)

case class Day(dayNo: Int, name: String)

object View {

  def getCurrentWeek(): View = {

    val now = Calendar.getInstance()

    val weekNo = now.get(Calendar.WEEK_OF_YEAR)

    val dayOfWeek = now.get(Calendar.DAY_OF_WEEK)
    // Move back to first day of week
    now.add(Calendar.DAY_OF_YEAR, -dayOfWeek + 1)

    val dayCount = 5
    val days: Buffer[Day] = new ArrayBuffer[Day]()

    for (i <- 1 to dayCount) {
      now.add(Calendar.DAY_OF_YEAR, 1)
      val dateFormat = new SimpleDateFormat("EEEE d/M")
      days += Day(now.get(Calendar.DAY_OF_YEAR), dateFormat.format(now.getTime()))
    }

    View(days.toList, weekNo.toString())
  }

}