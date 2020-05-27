class Point(var _x: Int, var _y: Int, var map_size: Int, var infection_time: Int, var daily_death_rate: Double){
  private var _infected = false
  private var _dead = false

  def dead = _dead
  def kill(): Unit = {
    _dead = true
    _infected = false
  }
  def x = _x
  def bounds = map_size
  def bounds_= (newValue: Int): Unit = {
    if(_x < newValue && _y < newValue) map_size = newValue else printWarning
  }
  def x_= (newValue: Int): Unit = {
    if (newValue < map_size) _x = newValue else printWarning
  }

  def y = _y
  def y_= (newValue: Int): Unit = {
    if (newValue < map_size) _y = newValue else printWarning
  }
  def infected = _infected
  def recovered = infection_time < 0
  def infect(): Unit = {
    if(infection_time < 0) return;
    _infected = true

  }

  private def get_a_move(range: Int): (Int, Int) = {
    var randomizer = scala.util.Random
    var x_direction = randomizer.nextInt(2* range) - range
    var y_direction = randomizer.nextInt(2* range) - range
    if(_infected){
      val death = randomizer.nextInt(10000) <= daily_death_rate*10000
      if(death) kill()
    }
    return (x_direction, y_direction)
  }
  def make_a_move(range: Int): Unit = {
    if(_infected) infection_time = infection_time -1
    if(infection_time < 0) _infected = false
    var (p, k) = get_a_move(range)
    x = (x + p)%map_size
    y = (y + k)%map_size
    if(x<0) x = x + map_size
    if(y<0) y = y + map_size
  }
  def print(): Unit = {
    println(x, y, (if(infected) " INFECTED" else if(infection_time < 0) " Recovered" else " Healthy") )
  }
  def distance(other: Point): Double = {
    return scala.math.sqrt(scala.math.pow((other.x-x),2) + scala.math.pow((other.y-y),2))
  }

  private def printWarning = println("WARNING: Point out of bounds")
}