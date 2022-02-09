import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import puke.Puke;
import puke.exception.PukeException;
import puke.ui.MainWindow;

/**
 * Main class for Puke.
 */
public class PukeMain extends Application {
    private Puke puke = new Puke("data/puke.txt");

    @Override
    public void start(Stage stage) {
        try {
            puke.loadTasksFromFile();

            FXMLLoader fxmlLoader = new FXMLLoader(PukeMain.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPuke(puke);

            stage.setOnCloseRequest(x -> {
                try {
                    puke.saveTasksToFile();
                } catch (PukeException e) {
                    e.printStackTrace();
                }
            });

            stage.show();
        } catch (IOException | PukeException e) {
            e.printStackTrace();
        }
    }
}
