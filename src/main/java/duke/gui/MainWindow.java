package duke.gui;

import java.io.IOException;

import duke.CommandExecutor;
import duke.exceptions.RequiredInformationMissingException;
import duke.exceptions.UnknownCommandException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
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

    private CommandExecutor commandExecutor;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/rottweiler.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/samoyed.png"));

    /**
     * Initialises a s
     */
    @FXML
    public void initialize() {
        String introMsg = "Hello, I'm Duke, your trusty doggo assistant!\nHere's what i can do:\n\n"
                + "Add a event:\nevent <task name> /at <time & date>\n\n"
                + "Add a todo task:\ntodo <task name>\n\n"
                + "Add a deadline:\ndeadline <task name> /at <time & date>\n\n"
                + "See number list of tasks:\nlist\n\n"
                + "Mark a task as done:\nmark <task number>\n\n"
                + "Unmark a task as done:\nunmark <task number>\n\n"
                + "Delete a task:\ndelete <task number>\n\n"
                + "Find tasks matching a keyword:\nfind <task name>\n\n"
                + "Exit the chat:\nbye";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(introMsg, dukeImage));
    }

    public void setCommandExecutor(CommandExecutor ex) {
        commandExecutor = ex;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = "";
        try {
            response = commandExecutor.executeCommand(input);
        } catch (RequiredInformationMissingException e) {
            response = "I need to know more details regarding your command!";
        } catch (UnknownCommandException e) {
            response = "I don't know what that command is!";
        } catch (IOException e) {
            response = "A problem occured saving to the tasks file.";
        } finally {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        }
    }
}
