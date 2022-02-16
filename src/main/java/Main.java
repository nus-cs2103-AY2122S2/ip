import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final Duke CHATBOT = new Duke("/data/tasks.txt");
    private final Image icon = new Image(this.getClass().getResourceAsStream("/images/icon.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(CHATBOT);
            stage.getIcons().add(icon);
            stage.setTitle("Batman");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

