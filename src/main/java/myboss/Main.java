package myboss;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.application.Platform;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private MyBoss myBoss = new MyBoss();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMyBoss(myBoss);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exitPlatform() {
        Platform.exit();
    }
}