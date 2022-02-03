package chatbot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Task management chat bot.
 */
public class ChatBot extends Application {
    public static final String APPLICATION_NAME = "Delphine v1.0";

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ChatBot.class.getResource("/view/MainWindow.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle(APPLICATION_NAME);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}