package ann;
import java.io.IOException;

import ann.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Ann using FXML.
 */
public class Main extends Application {

    private final Ann ann = new Ann();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            ann.init();
            fxmlLoader.<MainWindow>getController().setAnn(ann);
            stage.show();
            stage.setTitle("AnnBot");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
