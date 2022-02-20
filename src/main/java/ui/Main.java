package ui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String APP_PATH = "/test";

    private final Duke duke;

    /**
     * Instantiates a Main object that represents the entry point to the GUI
     * of Duke.
     *
     * @throws Exception If the chatbot fails to initialize.
     */
    public Main() throws Exception {
        this.duke = new Duke(Main.APP_PATH);
    }

    @Override
    public void start(Stage stage) {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            final AnchorPane ap = fxmlLoader.load();
            final Scene scene = new Scene(ap);
            stage.setTitle("Duke");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this.duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
