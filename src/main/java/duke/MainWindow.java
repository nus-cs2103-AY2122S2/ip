package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Breezy.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Siri.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMsg = "Hi I'm Seeree! Feel free to tell me any tasks you'd like!";
        String formatNote = "NOTE: For events and deadlines format for dates:YYYY-MM-DD. " +
                "Adding time for events and deadlines are optional, but the format is in the form HH:mm";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcomeMsg, dukeImage));
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(formatNote, dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws DukeException, IOException {
        String input = userInput.getText();
        if (input.trim().equals("bye")) {
            Platform.exit();
        }
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
