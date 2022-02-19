package yale.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import yale.Yale;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Yale yale = new Yale();
    private Image yaleIcon = new Image(this.getClass().getResourceAsStream("/images/Yale.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("YALE");
            stage.getIcons().add(yaleIcon);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setYale(yale);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
