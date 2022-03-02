// Adapted from
// - https://se-education.org/guides/tutorials/javaFxPart1.html
// - https://se-education.org/guides/tutorials/javaFxPart4.html

package heylo.ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import heylo.Main;
import heylo.tasks.Task;

/**
 * A GUI for Heylo using FXML.
 */
public class App extends Application {

    private Main main = new Main();

    @Override
    public void start(Stage stage) {
        try {
            Task.getSavedTasks();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Heylo");
            fxmlLoader.<MainWindow>getController().setMain(main);
            stage.show();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    Task.saveTasks();
                    System.exit(0);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}