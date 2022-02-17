package gui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Mike using FXML.
 */
public class Main extends Application {

    /** Instance of Mike for GUI to operate on. */
    private Duke duke = new Duke();

    /**
     * Triggers JavaFX application for Mike.
     *
     * @param stage Primary window of JavaFX.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getRoot().setStyle("-fx-font-family: 'serif'");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this.duke);
            stage.setTitle("Mike");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}