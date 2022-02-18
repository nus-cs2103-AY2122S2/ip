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
            scene.getStylesheets().add("/view/style.css");
            stage.setScene(scene);
            stage.setTitle("MNSKY");
            fxmlLoader.<MainWindow>getController().setMnsky(mnsky);
            fxmlLoader.<MainWindow>getController().displayResponse("hi", false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
