package mcbot.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import mcbot.McBotGui;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private McBotGui mcBot = new McBotGui();

    @Override
    public void start(Stage stage) {
        try {
//            Image image = new Image(this.getClass().getResourceAsStream("/images/bg.png"));
//            BackgroundImage bgImage = new BackgroundImage(image, 
//                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
//                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
//            Background background = new Background(bgImage);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMcBotGui(mcBot);
            fxmlLoader.<MainWindow>getController().sayHello();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}