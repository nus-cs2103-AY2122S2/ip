package ari;

import java.io.IOException;

import ari.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Ari using FXML.
 */
public class Main extends Application {

    private static final String FILE_PATH = "data/ari.txt";
    private static final String PROGRAM_NAME = "Ari";
    private static final String MAIN_WINDOW_PATH = "/view/MainWindow.fxml";

    private Ari ari = new Ari(FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(MAIN_WINDOW_PATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle(PROGRAM_NAME);
            stage.setResizable(false);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAri(ari);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
