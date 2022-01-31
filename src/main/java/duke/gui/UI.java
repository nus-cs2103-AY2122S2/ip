package duke.gui;

import duke.CommandExecutor;
import duke.exceptions.UnknownFileEntry;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;


/**
 * Main duke.gui.UI interface class.
 */
public class UI extends Application {
    private CommandExecutor commandExecutor;

    @Override
    public void init() {
        try {
            this.commandExecutor = new CommandExecutor();
        } catch (IOException e) {
            System.out.println("An error occured accessing the stored tasks file.");
            System.out.println("Error Message: " + e.getMessage());
            System.exit(-1);
        } catch (UnknownFileEntry e) {
            System.out.println("Unrecognised entries in stored tasks file.");
            System.exit(-1);
        }
    }

    /**
     * Starts the GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(UI.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCommandExecutor(commandExecutor);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
