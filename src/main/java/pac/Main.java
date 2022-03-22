package pac;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Pac using FXML.
 */
public class Main extends Application {

    private Pac pac = new Pac("data/pac.txt");

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Pac");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPac(pac);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
