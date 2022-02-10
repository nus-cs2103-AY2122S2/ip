package dooke;

import java.io.IOException;

import dooke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Dooke dooke = new Dooke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
//            stage.getIcons().add(BOT_ICON);
            fxmlLoader.<MainWindow>getController().setDooke(dooke);
            stage.setTitle("Dooke");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
