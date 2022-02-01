package duke;

import duke.exception.DukeException;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/JJBAUser.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the instance of Duke created and initalise the Duke Bot.
     *
     * @param d the instance of duke being passed in.
     */
    public void setDuke(Duke d) {
        duke = d;

        Image dukeImage = new Image(this.getClass().getResourceAsStream(duke.getBotImagePath()));;

        try {
            duke.initializeStorageSystem();
        } catch (DukeException e) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(e.getMessage(), dukeImage)
            );
        }

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.getWelcomeMessage(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        if (response != null) {

            Image dukeImage = new Image(this.getClass().getResourceAsStream(duke.getBotImagePath()));;

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        }
    }
}
