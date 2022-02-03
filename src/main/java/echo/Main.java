package echo;

import java.io.IOException;
import java.nio.file.Paths;

import echo.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Echo echo = new Echo(Paths.get("").toAbsolutePath() + "/data/echo.txt");

    /**
     * Starts GUI for Echo using JavaFX.
     * @param stage Stage
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Echo");
            stage.setResizable(false);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setEcho(echo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
