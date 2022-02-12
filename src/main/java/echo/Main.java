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

    private Echo echo = new Echo(getPath());

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
            fxmlLoader.<MainWindow>getController().greet();
            fxmlLoader.<MainWindow>getController().loadFile();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Returns the absolute path of Echo save file.
     *
     * @return Absolute path of save file.
     */
    private String getPath() {
        String currentPath = Paths.get("").toAbsolutePath().toString();
        return currentPath.replace("\\", "/") + "/data/echo.txt";
    }
}
