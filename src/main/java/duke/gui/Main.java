package duke.gui;

import java.io.IOException;

import duke.ui.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("C:/repos/ip/data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            //Step 1. Setting up required components
            Label helloWorld = new Label("Hello World!"); // Creating a new Label control
            Scene scene1 = new Scene(helloWorld); // Setting the scene to be our Label
            stage.setScene(scene1); // Setting the stage to show our screen

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
