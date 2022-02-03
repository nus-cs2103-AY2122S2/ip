package taskmaster.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import taskmaster.Taskmaster;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Taskmaster taskmaster = new Taskmaster();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Taskmaster");
            stage.setScene(scene);
            stage.getIcons().add(new Image("/images/nani.png"));
            fxmlLoader.<MainWindow>getController().setTaskmaster(taskmaster);
            fxmlLoader.<MainWindow>getController().greet();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
