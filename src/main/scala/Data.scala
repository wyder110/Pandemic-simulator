import scala.collection.mutable.ArrayBuffer

class Data(val board_size: Int, val points_number: Int, val infected_points_at_start: Int, val move_range: Int, val infection_range: Double, val infection_ratio: Double, val infection_time: Int, val death_rate: Double) {
  val days: ArrayBuffer[Day] = new ArrayBuffer()

  def addDay(whichDay: Int, alive: Int, infected: Int, recovered: Int){
    val newDay = new Day(whichDay, alive, infected, recovered)
    days += newDay
  }

  def getDay(dayNumb: Int) : Day = {
    return days(dayNumb)
  }

  override def toString() : String = {
    var str = ""
    for (day <- days) {
      str += day.toString() + "\n"
    }
    return str
  }

  class Day(val whichDay: Int, val alive: Int, val infected: Int, val recovered: Int){
    override def toString() : String = {
      return "Day: "+whichDay + " Alive: "+alive +" Infected: "+infected + " Recovered: "+recovered
    }

  }
}