package duke.gui;

import java.io.IOException;
import java.util.Timer;

import duke.Duke;
import duke.reminder.RemindersTask;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
            stage.setResizable(false);
            stage.setTitle("Jarvis");
            stage.show();

            // Starts the Reminder feature
            launchReminders();

            // Adds a listener to close the Reminder feature when the stage is closed.
            stage.setOnCloseRequest(event -> {
                closeReminders();
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("e: " + e.getMessage());
        }
    }

    /**
     * Launches the task reminder function, either as repeated reminders or a one-time reminder.
     */
    public void launchReminders() {
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


