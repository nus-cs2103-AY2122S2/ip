package luca;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import luca.ui.MainWindow;

/**
 * Set up the GUI for Luca using FXML.
 */
public class Main extends Application {

    /** Luca chat bot used for logic. */
    private Luca luca = new Luca("/data/Luca.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/view/Stylesheet.css").toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLuca(luca);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
