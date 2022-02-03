package main;
import java.io.IOException;

import GUI.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Tesseract using FXML.
 */
public class Main extends Application {

    private Tesseract tesseract = new Tesseract();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/css/display.css").toExternalForm());

            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTesseract(tesseract);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
