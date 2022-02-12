package juke.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import juke.command.Result;
import juke.ui.controller.MainWindow;

/**
 * Manages the graphical user interface using JavaFX.
 */
public class Gui {

    private Scene scene;
    private MainWindow mainWindow;

    /**
     * Initializes the components of the UI.
     *
     * @param stage Main stage.
     */
    public void initializeUiComponents(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainWindow.fxml"));
            this.mainWindow = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setGui(this);

            this.scene = new Scene(mainWindow);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a formatted string for Juke response.
     *
     * @param input Input string.
     * @return Formatted string.
     */
    public String getResponse(String input) {
        return ">>>>>> " + input;
    }

    /**
     * Prints the message associated with a success, or the error message otherwise.
     *
     * @param result Result of a command execution.
     */
    public String displayResult(Result result) {
        try {
            String[] string = result.getOrThrow();
            return String.join(System.lineSeparator(), string);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
