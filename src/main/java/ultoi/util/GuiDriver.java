package ultoi.util;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A GUI for Duke using FXML.
 */
public class GuiDriver extends Application {
    @Override
    public void start(Stage stage) {
        Path filePath = Paths.get(System.getProperty("user.home"),
                "iP", "data", "Ultoi.txt");
        Ultoi ultoi = new Ultoi(filePath);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GuiDriver.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDriver(ultoi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
