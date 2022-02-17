import taskie.Taskie;
import taskie.gui.MainWindow;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Taskie using FXML.
 */
public class Main extends Application {

    private Taskie taskie = new Taskie("data/data.bin");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Taskie");
            stage.getIcons().add(new Image("/images/Icon.png"));
            fxmlLoader.<MainWindow>getController().setDuke(taskie);
            fxmlLoader.<MainWindow>getController().greet();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
