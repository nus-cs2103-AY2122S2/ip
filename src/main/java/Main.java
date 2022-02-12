import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            stage.setTitle("Dodo");
            // Set the scene of the interface.
            stage.setScene(scene);

            // Set duke into the interface.
            fxmlLoader.<MainWindow>getController().setDuke(duke);

            // show the interface onto user's screen.
            stage.show();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
