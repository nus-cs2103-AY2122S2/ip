package chatbot.gui;

import java.io.IOException;
import java.util.Objects;

import chatbot.ChatBot;
import chatbot.gui.controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for innkeeper using FXML.
 */
public class Main extends Application {

    private ChatBot innkeeper = new ChatBot();


    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setInnkeeper(innkeeper);
            stage.setTitle("Innkeeper ChatBot");
            stage.getIcons().add(new Image(Objects.requireNonNull(
                    Main.class.getResourceAsStream("/images/hslogo.jpg")
            )));
            fxmlLoader.<MainWindow>getController().start(innkeeper.getGreeting());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
