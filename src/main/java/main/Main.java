package main;

import java.io.IOException;

import jarvis.Jarvis;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.MainWindow;

/**
 * A GUI for Jarvis using FXML.
 */
public class Main extends Application {

    private final Jarvis jarvis = new Jarvis();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("J.A.R.V.I.S. - Your personal assistant");
            fxmlLoader.<MainWindow>getController().setJarvis(jarvis);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

