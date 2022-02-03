package chatbot;

import chatbot.gui.MainWindowController;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Task management chat bot.
 */
public class ChatBot extends Application {
    public static final String APPLICATION_NAME = "Delphine v1.0";

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/MainWindow.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));
            fxmlLoader.<MainWindowController>getController().setTerminateCallback(() -> {
                PauseTransition delay = new PauseTransition(Duration.seconds(3));
                delay.setOnFinished(event -> {
                    stage.close();
                });
                delay.play();
            });
            stage.setTitle(APPLICATION_NAME);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}