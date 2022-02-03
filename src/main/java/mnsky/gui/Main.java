package mnsky.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mnsky.core.Mnsky;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Mnsky mnsky = new Mnsky();

    /**
     * Starts the stage for the JavaFX GUI.
     * @param stage The stage for the JavaFX GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMnsky(mnsky);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
