package saitama;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import saitama.ui.MainWindow;

/**
 * A GUI for Saitama using FXML.
 */
public class Main extends Application {
    private static String filePath = "data/Saitama.txt";
    private Saitama saitama = new Saitama(filePath);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("SaiTasker");
            stage.getIcons().add(new Image("images/logo.png"));
            fxmlLoader.<MainWindow>getController().setSaitama(saitama);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
