package wonka;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Wonka wonka = new Wonka();
    private final Image appIcon = new Image(this.getClass().getResourceAsStream("/images/wonka.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<wonka.gui.MainWindow>getController().setWonka(wonka);
            fxmlLoader.<wonka.gui.MainWindow>getController().setStage(stage);
            stage.getIcons().add(appIcon);
            stage.show();
            stage.setTitle("Wonka");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
