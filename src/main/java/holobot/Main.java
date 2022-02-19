package holobot;

import java.io.IOException;

import holobot.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * A GUI for HoloBot using FXML.
 */
public class Main extends Application {

    private HoloBot holoBot = new HoloBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            Font.loadFont(Main.class.getResource("/font/KristenITC.ttf").toExternalForm(), 13);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("HoloBot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setHoloBot(holoBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
