package alfred;

import alfred.ui.controller.MainWindow;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Alfred using FXML.
 */
public class Main extends Application {

    private final Alfred alfred = new Alfred();

    @Override
    public void start(Stage stage) {
        // initialize
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAlfred(alfred);
            stage.show();

            // greeting
            fxmlLoader.<MainWindow>getController().greetUser();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
