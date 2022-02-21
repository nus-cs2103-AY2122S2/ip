package juke.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import juke.Juke;
import juke.command.Result;
import juke.ui.controller.MainWindow;

/**
 * Manages the graphical user interface using JavaFX.
 */
public class Gui {
    private static final String STAGE_TITLE = "Juke";
    private static final String GREET_MESSAGE = "Greetings Executor!";
    private static final String RESOURCE_LOCATION = "/view/MainWindow.fxml";

    /**
     * Reference to the Juke instance.
     */
    private final Juke juke;

    private Scene scene;
    private MainWindow mainWindow;

    /**
     * Constructor to initialize GUI.
     *
     * @param instance Instance of Juke.
     */
    public Gui(Juke instance) {
        this.juke = instance;
    }

    /**
     * Initializes the components of the UI.
     *
     * @param stage Main stage.
     */
    public void initializeUiComponents(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(RESOURCE_LOCATION));
            Parent root = fxmlLoader.load();

            scene = new Scene(root);

            mainWindow = fxmlLoader.<MainWindow>getController();
            mainWindow.setJuke(juke);

            stage.setScene(scene);
            stage.setTitle(STAGE_TITLE);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the message associated with a success, or throws the exception associated with the error.
     *
     * @param result Result of a command execution.
     * @return Message.
     * @throws Exception Exception if it is an error
     */
    public String getResultMessageOrThrow(Result result) throws Exception {
        String[] string = result.getOrThrow();
        return String.join(System.lineSeparator(), string);
    }

    /**
     * Prints the welcome message.
     */
    public void greet() {
        mainWindow.addJukeDialog(GREET_MESSAGE);
    }
}
