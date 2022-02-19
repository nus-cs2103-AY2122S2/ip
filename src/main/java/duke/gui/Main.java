package duke.gui;

import java.io.IOException;
import java.util.Objects;

import duke.Duke;
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

    private final Duke duke = new Duke();
    private final Image appImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/pirate.png")));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke, stage);

            // Set name of application and picture for icon
            stage.setTitle("Cap'n Dave");
            stage.getIcons().add(appImage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
