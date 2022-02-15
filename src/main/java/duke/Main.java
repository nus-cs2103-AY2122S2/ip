package duke;

import java.io.IOException;
import java.text.ParseException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {


    private Duke duke = new Duke("data/tasks.txt");

    public Main() throws IOException, ParseException {

    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.getIcons().add(new Image(String.valueOf(Main.class.getResource("/images/Duke.png"))));
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert stage.isShowing() : "stage should be showing";
    }
}
