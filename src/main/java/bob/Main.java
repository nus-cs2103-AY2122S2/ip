package bob;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Bridges the gap between Bob logic and GUI.
 */
public class Main extends Application {
    private Bob bob = new Bob();

    /**
     * Starts an application.
     *
     * @param stage Stage for JavaFX.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setResizable(false);
            stage.setTitle("BOB");
            stage.setScene(scene);
            assert bob != null;
            fxmlLoader.<MainWindow>getController().setBob(bob);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
