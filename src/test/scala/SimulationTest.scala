class SimulationTest extends org.scalatest.FunSuite {
  var simulation = new Simulation()


  for( board_size <- 1000 to 1010; points_number <- 10000 to 10010; infected_number <- 10 to 20; infection_time <- 1 to 10){
    simulation.make_simulation(board_size,points_number,infected_number,board_size/2,board_size/10,infection_time/100,infection_time,board_size/100);
  }
}
