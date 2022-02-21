package duke;

import java.io.IOException;
import java.net.URL;

import duke.components.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    // File path name.
    private static final String DUKE_FILE = "data/duke.txt";
    private static final String MAIN_WINDOW_XML_FILE = "/view/MainWindow.fxml";
    private static final String TITLE = "PC Principal";
    // Error messages.
    private static final String WINDOW_NOT_FOUND_ERROR_MESSAGE = "Main Window File not found";

    private Duke duke = new Duke(DUKE_FILE);

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle(TITLE);
            // Checks if /view/MainWindow.fxml file exists
            URL mainWindowFile = Main.class.getResource(MAIN_WINDOW_XML_FILE);
            assert mainWindowFile != null : WINDOW_NOT_FOUND_ERROR_MESSAGE;
            // Loads the Main Window.
            FXMLLoader fxmlLoader = new FXMLLoader(mainWindowFile);
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
