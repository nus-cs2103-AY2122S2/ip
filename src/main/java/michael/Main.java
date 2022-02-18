package michael;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import michael.gui.MainWindow;

/**
 * A GUI for Michael using FXML.
 */
public class Main extends Application {

    private Michael michael;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Michael");
            stage.getIcons().add(new Image("/images/TitleImg.png"));
            michael = new Michael(stage);
            fxmlLoader.<MainWindow>getController().setMichael(michael);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
