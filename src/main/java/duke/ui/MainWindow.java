package duke.ui;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import duke.Duke;
import duke.Main;
import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * This control represents the main window of the GUI, consisting of an AnchorPane which contains a dialogue container,
 * a text field and a send button.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates a new main window.
     *
     * @param stage the stage of the application
     */
    public MainWindow(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);

            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Poogie");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets up the main window.
     * Binds the scroll pane to the height of the dialogue container, so it follows the latest dialogue box.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets a duke object in the main window.
     *
     * @param duke the duke object to be set
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Sets a ui object in the main window.
     *
     * @param ui the ui object to be set
     */
    public void setUi(Ui ui) {
        this.ui = ui;
    }

    /**
     * Sets a storage object in the main window.
     *
     * @param storage the storage object to be set
     */
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * Sets a TaskList object in the main window.
     *
     * @param tasks the TaskList object to be set
     */
    public void setTaskList(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        Command command = new Parser().parseCommand(userText);
        try {
            String dukeText = command.execute(tasks, ui, storage);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, userImage),
                    DialogBox.getDukeDialog(dukeText, dukeImage)
            );
        } catch (DukeException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, userImage),
                    DialogBox.getDukeDialog(e.getMessage(), dukeImage)
            );
        } finally {
            userInput.clear();
            if (command.isExit()) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.exit();
                    }
                }, 2000);
            }
        }
    }
}
