package gui;

import java.io.IOException;

import bot.Hal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Hal hal = new Hal();

    /**
     * The start method that overrides the Application's start method, to be executed to display the stage.
     * @param stage JavaFX stage object to be displayed.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setHal(hal);
            fxmlLoader.<MainWindow>getController().displayGreeting();
            stage = fxmlLoader.<MainWindow>getController().changeSettings(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
