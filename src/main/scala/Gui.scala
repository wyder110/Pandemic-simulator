import java.awt

import scala.swing.event.ButtonClicked
import swing._
import org.jfree.chart.{ChartFactory, ChartPanel}
import org.jfree.data.category.DefaultCategoryDataset
import org.jfree.chart.plot.PlotOrientation


object Gui extends SimpleSwingApplication{

  def newField(txt: String) = new TextField {
    text = txt
    columns = 10
  }
  val board_size = newField("100")
  val points_number = newField("1000")
  val infected_points = newField("10")
  val move_range = newField("4")
  val infection_range = newField("2")
  val infection_ratio = newField("100")
  val infection_time = newField("3")
  val death_rate = newField("2")

  val startButton = new Button("START")



    def top = new MainFrame {
      title = "Simulation informations"
      //    contents = new FlowPanel(new Label(" Board size  =  "), board_size,
      //      new Label(" Objects number"), points_number)
      contents = new BoxPanel(Orientation.Vertical){
        contents += new FlowPanel(new Label("Board size  =  "), board_size)
        contents += new FlowPanel(new Label("Population size  =  "), points_number)
        contents += new FlowPanel(new Label("Preinfected objects number  =  "), infected_points)

        contents += new FlowPanel(new Label("Single turn move range  =  "), move_range)
        contents += new FlowPanel(new Label("Infection range  =  "), infection_range)
        contents += new FlowPanel(new Label("Infection ratio (%) =  "), infection_ratio)
        contents += new FlowPanel(new Label("Average infection time  =  "), infection_time)
        contents += new FlowPanel(new Label("Daily death rate (%) =  "), death_rate)
        contents += new FlowPanel(startButton)
      }

      listenTo(startButton)
      reactions += {
        case ButtonClicked(component) if component == startButton =>
//          println("Board size  =   " + board_size.text)
//          println("Population size  =   " + points_number.text)
//          println("Preinfected objects number = " + infected_points.text)
          val simulation = new Simulation()

          val data = simulation.make_simulation(board_size.text.toInt,points_number.text.toInt,infected_points.text.toInt,move_range.text.toInt,
            infection_range.text.toDouble,infection_ratio.text.toDouble/100,infection_time.text.toInt, death_rate.text.toDouble/100)
          println(data.toString())
          val toChart = new DefaultCategoryDataset
          for (day <- data.days) {
            toChart.addValue(day.infected, "Infected", day.whichDay)
          }

          val chart = ChartFactory.createLineChart(
            "Pandemic", "Days", "Value",
            toChart, PlotOrientation.VERTICAL,
            true, true, true)

          peer.setContentPane(new ChartPanel(chart))

          new ShowData(data).top.visible = true
          this.close()

      }

  }

}
