package doge;

import java.io.File;
import java.io.IOException;

import doge.Doge;
import doge.view.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Doge doge = new Doge();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(new File("./src/main/resource/view/MainWindow.fxml").toURI().toURL());
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDoge(this.doge);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
