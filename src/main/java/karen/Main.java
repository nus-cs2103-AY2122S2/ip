package karen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import karen.gui.MainWindow;

import java.io.IOException;

public class Main extends Application {
    public static final String USER_IMAGE_PATH = "/images/DaUser.png";
    public static final String KAREN_IMAGE_PATH = "/images/DaDuke.png";

    private final Karen karen = new Karen();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Adapted from JavaFx Tutorial:
     * https://se-education.org/guides/tutorials/javaFx.html
     * @param stage Base for GUI
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKaren(this.karen);
            stage.show();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

}
