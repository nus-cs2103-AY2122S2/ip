package chatcat.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import chatcat.ChatCat;

/**
 * Code for GUI was referenced from se-education.org
 * https://se-education.org/guides/tutorials/javaFx.html
 */

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final ChatCat chatcat = new ChatCat();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChatCat(chatcat);
            stage.setTitle("ChatCat");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}