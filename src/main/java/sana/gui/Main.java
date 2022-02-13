package sana.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
            stage.setTitle("Sana");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
