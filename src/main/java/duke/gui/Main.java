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

            //
            AtomicBoolean shuttingDown = new AtomicBoolean(false);

            // Sets up the Reminder Timer
            Thread thread = new Thread(() -> {
                while (!shuttingDown.get() && !Thread.interrupted()) {
                    launchReminders();
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
            //launchReminders();

            // Adds a listener to execute a function when the stage is closed.
            stage.setOnCloseRequest(event -> {
                System.out.println("Stage is closing");
                shuttingDown.set(true);
                thread.interrupt();
                closeReminders();
                Platform.exit();
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
            timer.scheduleAtFixedRate(tasks, 5_000, 10_000);
        } else {
            timer.schedule(tasks, 5_000);
        }
    }

    public void closeReminders() {
        timer.cancel();
    }

}


