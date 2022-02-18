package arthur;

import java.io.IOException;

import arthur.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Arthur using FXML.
 */
public class Main extends Application {

    private static final String RESOURCE_LOCATION = "/view/MainWindow.fxml";
    private static final String ICON_LOCATION = "/images/Icon.png";
    private final Arthur arthur = new Arthur();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(RESOURCE_LOCATION));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Arthur");
            fxmlLoader.<MainWindow>getController().setArthur(arthur);
            stage.getIcons().add(new Image(ICON_LOCATION));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
