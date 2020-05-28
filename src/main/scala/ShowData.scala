import Main.{board_size, death_rate, infected_points, infection_range, infection_ratio, infection_time, move_range, newField, points_number, startButton}
import javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE
import org.jfree.chart.{ChartFactory, ChartPanel}
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.DefaultCategoryDataset

import scala.swing.event.ButtonClicked
import scala.swing.{BoxPanel, Button, Dimension, FlowPanel, Frame, Label, MainFrame, Orientation, SimpleSwingApplication, TextField}

class ShowData(data :Data) extends SimpleSwingApplication {

  def newField(txt: String) = new TextField {
    text = txt
    columns = 10
  }
  val whichDay = newField("3")
  val startButton = new Button("Get information")
//  for (day <- data.days) {
//    toChart.addValue(day.infected, "Infected", day.whichDay)
//    toChart.addValue(day.recovered, "Recovered", day.whichDay)
//    toChart.addValue(data.days(0).alive-day.alive, "Deaths", day.whichDay)
//  }


  def top = new MainFrame {

    import javax.swing.WindowConstants.HIDE_ON_CLOSE
    peer.setDefaultCloseOperation(HIDE_ON_CLOSE)

    title = "Simulation results"

    val deathsLabel = new Label("All deaths: " + (data.days(0).alive-data.days(data.days.length-1).alive))
    val recoveredLabel = new Label("All recovered: " + data.days(data.days.length-1).recovered)
    val notInfectedLabel = new Label("Not infected: " + (data.days(data.days.length-1).alive-data.days(data.days.length-1).recovered-data.days(data.days.length-1).infected))
    val infectedLabel = new Label("Infected at this moment: " + (data.days(data.days.length-1).infected))

    contents = new BoxPanel(Orientation.Vertical){
      contents += new FlowPanel(new Label("Pandemic lasted: " + (data.days.length-1) + " days"))

      contents += new FlowPanel(deathsLabel)

      contents += new FlowPanel(recoveredLabel)
      contents += new FlowPanel(notInfectedLabel)
      contents += new FlowPanel(infectedLabel)

      contents += new FlowPanel(new Label("Write day  =  "), whichDay,startButton)

    }

    listenTo(startButton)
    reactions += {
      case ButtonClicked(component) if component == startButton => {
        println("SET LABEL")
        deathsLabel.text = "Deaths to this point " + (data.days(0).alive-data.days(whichDay.text.toInt).alive)
        recoveredLabel.text = "All recovered: " + data.days(whichDay.text.toInt).recovered
        notInfectedLabel.text = "Not infected: " + (data.days(whichDay.text.toInt).alive-data.days(whichDay.text.toInt).recovered-data.days(whichDay.text.toInt).infected)
        infectedLabel.text = "Infected: " + data.days(whichDay.text.toInt).infected

      }
    }

  }

}
