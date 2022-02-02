package sana.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import sana.Sana;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    /** Sana */
    private Sana sana = new Sana();

    /**
     * Initialises the stage for the Sana GUI
     *
     * @param stage the stage
     */
    @Override
    public void start(Stage stage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSana(sana);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
