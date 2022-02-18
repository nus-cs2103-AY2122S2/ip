package duke.main;

import java.io.IOException;
import java.net.URL;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = Duke.ofGuiApplication();

    /**
     * Starts the application.
     *
     * @param stage The stage to be used by the application.
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Duke");
        try {
            URL filePath = Main.class.getResource("/view/MainWindow.fxml");
            assert filePath != null : "Some resource is missing, unable to proceed.";
            FXMLLoader fxmlLoader = new FXMLLoader(filePath);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().greet();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
