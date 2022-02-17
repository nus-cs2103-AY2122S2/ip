package kenobi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kenobi.ui.MainWindow;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class KenobiApp extends Application {
    private Kenobi kenobi = new Kenobi("../../../data/tasks.txt");

    private Image user = new Image(this.getClass().getResourceAsStream("/images/Penguin_pp.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Kenobi_pp.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKenobi(kenobi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

