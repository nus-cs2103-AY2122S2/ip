package duke.gui;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;

import duke.Duke;
import duke.reminder.RemindersTask;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert; //!
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            launchReminders();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("e: " + e.getMessage());
        }
    }

    public void launchReminders() {
        System.out.println("here again!");
        Date date = new Date();
        Timer timer = new Timer();
        RemindersTask tasks = new RemindersTask();
        //timer.scheduleAtFixedRate(tasks, date, 2000);
        timer.scheduleAtFixedRate(tasks, 2000, 5000);
    }

    private void buildReminderNotif() {
        Alert information = new Alert(Alert.AlertType.INFORMATION);
        information.setTitle("Warning Dialog!");
        information.setContentText("Warning");
        information.setHeaderText("warningz");
        information.showAndWait();
    }
}


