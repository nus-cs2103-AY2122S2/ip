package tsohg;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private final Tsohg tsohg = new Tsohg("data.txt");

    @Override
    public void start(Stage stage) {
        try {
            URL a = Main.class.getResource("/view/MainWindow.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            VBox ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTsohg(tsohg);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
