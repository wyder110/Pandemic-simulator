import scala.collection.mutable.ArrayBuffer

class Board(var map_size: Int){
  private val points: ArrayBuffer[Point] = new ArrayBuffer()

  def add_point(x:Int, y:Int, time:Int, daily_death_rate: Double): Unit = {
    var point = new Point(x, y, map_size, time, daily_death_rate)
    points += point
  }
  def print_points(): Unit = {
    for( each <- points){
      each.print()
    }
  }
  def move_points(range: Int): Unit = {
    for( each <- points){
      if(!each.dead){
        each.make_a_move(range)
      }
    }
  }
  def infect_points(points_number: Int): Unit = {
    if (points_number > points.size){
      println("WARNING: Too many infected points")
      return
    }
    for( i <- 0 to points_number -1){
      points(i).infect()
    }
  }
  def count_infected(): Int = {
    var infected = 0
    for(each <- points){
      if(!each.dead && each.infected) infected += 1
    }
    return infected
  }
  def count_recovered(): Int = {
    var recovered = 0
    for(each <- points){
      if(!each.dead && each.recovered) recovered += 1
    }
    return recovered
  }
  def count_alive(): Int = {
    var alive = 0
    for(each <- points){
      if(!each.dead) alive += 1
    }
    return alive
  }

  def check_new_infections(infection_range: Double, infection_ratio: Double): Unit= {
    // println("Checking new infections...")
    for(point <- points){
      if(!point.dead && point.infected){
        for(other_point <- points){
          if(point.distance(other_point) < infection_range){
            if(!other_point.dead && !other_point.infected){
              var randomizer = scala.util.Random
              var p = randomizer.nextInt(1000) <= infection_ratio*1000
              if(p) other_point.infect()
            }
          }
        }
      }
    }
  }
}
