package duke.gui;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.ui.Duke;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke;

    /**
     * Constructs a Main object.
     * duke variable is created with the file path: ~/FFdata/tasks.txt
     * , where ~ means the home directory.
     */
    public Main() {
        String home = System.getProperty("user.home");
        Path filePath = Paths.get(home, "FFdata", "tasks.txt");
        duke = new Duke(filePath.toString());
    }

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Friendly Friend");
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/Images/red.png")));

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
