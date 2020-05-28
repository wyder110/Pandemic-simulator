
class Simulation {

  def prepare_simulation(points_number : Int, map_size: Int, infection_time: Int, daily_death_rate: Double): Board = {
    var new_board = new Board(map_size)
    var randomizer = scala.util.Random
    for( i <- 1 to points_number){
      var time = scala.math.max(1, infection_time + randomizer.nextInt(10) -5)
      new_board.add_point(randomizer.nextInt(100), randomizer.nextInt(100), time, daily_death_rate);
    }
    return new_board
  }

  def make_simulation(board_size: Int, points_number: Int, infected_number: Int, move_range: Int, infection_range: Double, infection_ratio: Double, infection_time: Int, daily_death_rate: Double): Data ={
    var board = prepare_simulation(points_number, board_size, infection_time, daily_death_rate)
    board.infect_points(infected_number)
    println("STARTING SIMULATION WITH: "+"Total: "+points_number,"Infected: "+board.count_infected(), "Recovered: "+board.count_recovered())
    var day = 0

    val information = new Data(board_size, points_number, infected_number, move_range, infection_range, infection_ratio, infection_time, daily_death_rate)
    information.addDay(day,points_number,infected_number,0)

    while( board.count_infected() > 0 ){
      day += 1
      board.move_points(move_range)
      board.check_new_infections(infection_range, infection_ratio)
      information.addDay(day,board.count_alive(),board.count_infected(),board.count_recovered())

    }
    return information
  }
}
