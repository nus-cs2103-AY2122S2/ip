package gary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A GUI for Gary using FXML
 */
public class Main extends Application {

    private static Path file = Paths.get(".","gary.txt");
    private static Gary gary = new gary.Gary("./gary.txt");

    /**
     * Initialize relevant variables and start the GUI
     *
     * @param stage main stage
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGary(gary);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}