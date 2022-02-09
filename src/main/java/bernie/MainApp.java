package bernie;

import java.io.IOException;

import bernie.ui.InputResponder;
import bernie.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bernie using FXML.
 */
public class MainApp extends Application {

    private Bernie bernie = new Bernie();
    private InputResponder inputResponder = new InputResponder();
    private Image bernieImg = new Image(this.getClass().getResourceAsStream("/images/DaBernie.jpg"));
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Bernie");
            stage.getIcons().add(bernieImg);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBernie(bernie);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
