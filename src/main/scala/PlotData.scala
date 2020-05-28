import org.jfree.chart.{ChartFactory, ChartPanel}
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.DefaultCategoryDataset

import scala.swing.{BoxPanel, Dimension, FlowPanel, Label, MainFrame, Orientation, SimpleSwingApplication}

class PlotData(data :Data)  extends SimpleSwingApplication{
  val toChart = new DefaultCategoryDataset
  for (day <- data.days) {
    toChart.addValue(day.infected, "Infected", day.whichDay)
    toChart.addValue(day.recovered, "Recovered", day.whichDay)
    toChart.addValue(data.days(0).alive-day.alive, "Deaths", day.whichDay)
  }


  val chart = ChartFactory.createLineChart(
    "Pandemic", "Days", "Value",
    toChart, PlotOrientation.VERTICAL,
    true, true, true)

  def top = new MainFrame {
    import javax.swing.WindowConstants.HIDE_ON_CLOSE
    peer.setDefaultCloseOperation(HIDE_ON_CLOSE)

    title = "Pandemic"
    this.minimumSize = new Dimension(1200,800)
    contents = new BoxPanel(Orientation.Vertical) {
      contents += new FlowPanel(new Label("Simulation"))
    }
    peer.setContentPane(new ChartPanel(chart))

  }
}
