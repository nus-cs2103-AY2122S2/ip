package controllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Duke;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();

            MainWindow mainWindow = fxmlLoader.<MainWindow>getController();
            mainWindow.displayLoadMessage();
            mainWindow.displayGreeting();

            mainWindow.sendButton.setOnMouseClicked((event) -> {
                mainWindow.handleUserInput();
            });

            mainWindow.userInput.setOnAction((event) -> {
                mainWindow.handleUserInput();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
