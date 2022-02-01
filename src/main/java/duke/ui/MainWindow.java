package duke.ui;

import duke.command.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/** Controller for MainWindow. Provides the layout for the other controls. */
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserAvatar.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DukeAvatar.png"));

    /**
     * Makes the scrollPane scrollable.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Initializes the MainWindow by printing the startup message.
     */
    public void setupDisplay() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(duke.getStartupMessage(), dukeImage));
    }

    /**
     * Sets the 'duke' attribute of this to the given Duke instance.
     *
     * @param d The Duke instance to associate with this controller.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing Duke's reply, and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        checkIfBye(input);
    }

    private void checkIfBye(String input) {
        if (input.equals("bye")) {
            System.exit(0);
        }
    }
}
