import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Nexus using FXML.
 */
public class Main extends Application {

    private final Nexus nexus = new Nexus();

    /**
     * Starts the interface.
     * @param stage Stage to be set onto the interface.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Nexus");
            // Set the scene of the interface.
            stage.setScene(scene);

            // Set Nexus into the interface.
            fxmlLoader.<MainWindow>getController().setNexus(nexus);

            // show the interface onto user's screen.
            stage.show();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
