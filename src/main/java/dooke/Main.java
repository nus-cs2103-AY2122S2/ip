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
 * @author s7manth
 * @version 0.2
 */
public class Main extends Application {

    private Dooke dooke = new Dooke();

    /**
     * Overrides start method for starting the gui application.
     * @param stage The stage of the javafx application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDooke(dooke);
            formatWindow(stage, ap);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void formatWindow(Stage stage, AnchorPane mainLayout) {
        stage.setTitle("Dooke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        mainLayout.setPrefSize(400.0, 600.0);
    }
}
