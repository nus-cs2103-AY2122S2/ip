package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertTrue;

/**
 * This is a Main class that acts as the logic behind the starting
 * of the Duke JavaFX application.
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-2-1
 */

public class Main extends Application {
    private Duke duke = new Duke();

    /**
     * Starts up the GUI of the Duke application with JavaFX
     * @param stage is the screen of the Duke application
     */
    @Override
    public void start(Stage stage) throws Exception {
        try {
            assertTrue(Files.exists(Path.of("src/main/resources/duke/view/MainWindow.fxml"))); //Assumes there is this file in the project
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/duke/view/MainWindow.fxml"));
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