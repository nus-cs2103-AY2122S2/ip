package connor;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class for GUI.
 */
public class Main extends Application {

    /**
     * Starts the GUI.
     *
     * @param stage The main stage of the GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = loader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("ConnorBot v1.1.0");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
