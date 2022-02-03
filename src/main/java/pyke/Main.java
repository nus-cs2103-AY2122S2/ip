package pyke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pyke.ui.controller.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Pyke pyke = new Pyke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            pyke.UiInit();
            fxmlLoader.<MainWindow>getController().setPyke(pyke);
            stage.setTitle("Pyke");
            stage.getIcons().add(new Image(Pyke.class.getResourceAsStream("/images/icon.png")));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}