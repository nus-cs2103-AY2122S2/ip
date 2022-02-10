package duke.gui;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;

import duke.Duke;
import duke.reminder.RemindersTask;
import javafx.application.Application;
import javafx.application.Platform;
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
    private Timer timer;
    private boolean repeatingReminders = true;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();

            // Starts the Reminder feature
            launchReminders();

            // Adds a listener to close the Reminder feature when the stage is closed.
            stage.setOnCloseRequest(event -> {
                System.out.println("Stage is closing");
                closeReminders();
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("e: " + e.getMessage());
        }
    }

    public void launchReminders() {
        System.out.println("here @ launchReminders!");
        timer = new Timer();
        RemindersTask tasks = new RemindersTask();
        if (repeatingReminders) {
            // First reminder alert dialog appears 5 seconds after launching the app, then every 60 seconds afterwards.
            timer.scheduleAtFixedRate(tasks, 5_000, 60_000);
        } else {
            // First & only reminder alert dialog appears 5 seconds after launching the app.
            timer.schedule(tasks, 5_000);
        }
    }

    public void closeReminders() {
        timer.cancel();
    }

}


