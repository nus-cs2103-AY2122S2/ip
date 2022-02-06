package karen;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import karen.gui.MainWindow;


public class Main extends Application {
    public static final String USER_IMAGE_PATH = "/images/DaCat.png";
    public static final String KAREN_IMAGE_PATH = "/images/DaKaren.png";
    public static final String MAIN_WINDOW_PATH = "/view/MainWindow.fxml";

    private final Karen karen = new Karen();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Adapted from JavaFx Tutorial:
     * https://se-education.org/guides/tutorials/javaFx.html
     *
     * @param stage Base for GUI
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(MAIN_WINDOW_PATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            stage.setTitle("Karen");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKaren(this.karen);
            stage.show();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

}
