package duke.ui.gui;

import java.util.Objects;

import duke.Duke;
import duke.command.CommandResult;
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

    private Duke duke;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/user.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/robot.png")));

    /**
     * Initializes the Main Window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcomeMessage() {
        String welcomeMessage = duke.getWelcomeMessage();
        addDukeDialog(welcomeMessage);
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isEmpty()) {
            userInput.clear();
            return;
        }
        CommandResult response = submitInput(input);
        String message = response.toString();
        boolean isError = response.isError();
        addUserDialog(input);
        if (isError) {
            addDukeErrorDialog(message);
        } else {
            addDukeDialog(message);
        }
        userInput.clear();
    }

    private CommandResult submitInput(String input) {
        return duke.getResponse(input);
    }

    private void addUserDialog(String text) {
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(text, userImage)
        );
    }

    private void addDukeDialog(String text) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(text, dukeImage)
        );
    }

    private void addDukeErrorDialog(String text) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeErrorDialog(text, dukeImage)
        );
    }
}
