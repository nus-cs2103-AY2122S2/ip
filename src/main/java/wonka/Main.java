package wonka;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Wonka wonka = new Wonka();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<wonka.gui.MainWindow>getController().setWonka(wonka);
            fxmlLoader.<wonka.gui.MainWindow>getController().setStage(stage);
            stage.show();
            stage.setTitle("Wonka");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
