package duke.gui;

import java.io.IOException;

import duke.Duke;
import duke.gui.controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private final Duke duke = new Duke("data/tasks.txt");
    /**
     * Initialises Duke for logic handling and MainWindow for the GUI.
     * @param stage The GUI stage.
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Sparrow");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getRoot().setStyle("-fx-font-family: 'sans-serif'");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().welcome(duke.welcome());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
