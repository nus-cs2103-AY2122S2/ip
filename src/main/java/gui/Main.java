package gui;

import java.io.IOException;

import duke.Duke;
import duke.DukeException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 *
 * @author Jeffry Lum
 * Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
 * with minor modifications
 * @version CS2103T AY21/22 Sem 2
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Duke duke = new Duke();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Task Manager by Duke the Angry Bird");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException | DukeException e) {
            e.printStackTrace();
            stage.close();
        }
    }
}