package duke.ui;

import java.io.IOException;

import duke.Duke;
import duke.command.Command;
import duke.exception.DukeException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * A Controller for MainWindow.
 *
 * This controller provides the layout for the other controls.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets duke with the initialized instance and greets the user.
     *
     * @param duke The initialized Duke instance
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
        greetUser();
    }

    /**
     * Greets the user with a welcome message.
     */
    @FXML
    private void greetUser() {
        String greetMsg = duke.getTaskList().getNumOfTasks() == 0
                ? Ui.GREET_NEW_USER_MESSAGE
                : Ui.GREET_OLD_USER_MESSAGE;

        DialogBox dukeResponse = DialogBox.getDukeResponseDialog(greetMsg, dukeImage);
        dialogContainer.getChildren().addAll(dukeResponse);
    }

    /**
     * Handles and processes the user input.
     *
     * Creates the dialog boxes for echoing user input, Duke's reply
     * (response or error) and Duke's filtered task reminder message,
     * and then appends them to the dialog container.
     *
     * Clears the user input after processing.
     *
     * Disables the text field and button and closes the program
     * window in 10 seconds after user initiates the bye command.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();

        if (isInputEmpty(input)) {
            userInput.clear();
            return;
        }

        Command command = null;
        boolean isExit = false;

        try {
            command = duke.getCommand(input);
            String response = duke.getResponse(command);
            isExit = command.isExit();

            DialogBox user = DialogBox.getUserDialog(input, userImage);
            DialogBox dukeResponse = DialogBox.getDukeResponseDialog(response, dukeImage);
            dialogContainer.getChildren().addAll(user, dukeResponse);
        } catch (DukeException | IOException e) {
            DialogBox user = DialogBox.getUserDialog(input, userImage);
            DialogBox dukeError = DialogBox.getDukeErrorDialog(e.getMessage(), dukeImage);
            dialogContainer.getChildren().addAll(user, dukeError);
        } finally {
            String filteredTasksReminderMessage = duke.getFilteredTasksReminderMessage(command);

            if (!filteredTasksReminderMessage.isEmpty()) {
                DialogBox dukeReminder = DialogBox.getDukeReminderDialog(filteredTasksReminderMessage, dukeImage);
                dialogContainer.getChildren().addAll(dukeReminder);
            }
        }

        userInput.clear();

        if (isExit) {
            userInput.setDisable(true);
            sendButton.setDisable(true);

            PauseTransition termination = new PauseTransition(Duration.seconds(10));
            termination.setOnFinished(event -> Platform.exit());
            termination.play();
        }
    }

    /**
     * Checks if the input consists of only space.
     *
     * @param input String input
     * @return True if the input is empty, false otherwise
     */
    private boolean isInputEmpty(String input) {
        return input.trim().equals("");
    }
}
