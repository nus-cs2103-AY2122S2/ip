package angela.gui;

import java.io.IOException;

import angela.Angela;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String DIRECTORY = "data";
    private static final String FILE_NAME = "Duke.txt";
    private static final String PATH = DIRECTORY + "/" + FILE_NAME;
    private Angela angela = new Angela(PATH, DIRECTORY);

    public Main() throws IOException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Angela");
            // Allow the open window to be resized
            stage.setResizable(false);
            stage.setMinHeight(600.0);
            stage.setMinWidth(400.0);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAngela(angela);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
