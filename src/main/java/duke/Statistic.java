package duke;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * This is the Statistic class that displays a piechart in a new window that
 * represents the number of Tasks based on Task type
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-2-1
 */
public class Statistic extends AnchorPane implements Initializable {
    private int toDoTasks = 0;
    private int deadlineTasks = 0;
    private int eventTasks = 0;
    @FXML
    private PieChart pieChart;
    @FXML
    public void initDuke(Duke duke) {
        setNumOfTasks(duke.getTasks());
    }

    public void initialize() {
        setUpPieChart();
    }

    //@@Da9el00 hsiaojietng-reused
    //The source creates a Pie Chart based on fruits data hardcoded from https://gist.github.com/Da9el00/9c1e63a5ba8c84e0219bb7e59d7a5bad
    private void setUpPieChart() {
        ObservableList<PieChart.Data> pieChartData = observableArrayList(
                new PieChart.Data("Todos", toDoTasks),
                new PieChart.Data("Deadlines", deadlineTasks),
                new PieChart.Data("Events", eventTasks)
        );

        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " tasks: ", data.pieValueProperty()
                        )
                ));
        pieChart.getData().addAll(pieChartData);
    }
    //@@Da9el00

    private void setNumOfTasks(ArrayList<Task> tasks) {
        for (Task task : tasks) {
            switch (task.getType()) {
                case 'T':
                    toDoTasks ++;
                    break;
                case 'D':
                    deadlineTasks ++;
                    break;
                case 'E':
                    eventTasks ++;
                    break;
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
