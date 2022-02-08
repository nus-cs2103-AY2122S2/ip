package nikki;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nikki.gui.MainWindow;

import java.io.IOException;

public class Main extends Application {

    private Nikki nikki = new Nikki("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setNikki(nikki);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
