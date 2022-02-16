package duke.ui;

import java.util.Objects;

import duke.Duke;
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

    private static final String USER_IMAGE = "/images/DaUser.png";
    private static final String DUKE_IMAGE = "/images/DaDuke.png";
    private static final String DUKE_ERROR_IMAGE = "/images/DaDukeError.png";

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream(USER_IMAGE)));
    private final Image dukeImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream(DUKE_IMAGE)));
    private final Image dukeErrorImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream(DUKE_ERROR_IMAGE)));

    /**
     * Initializes the scollpane of the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = "";
        Image dukeResponseImage = null;

        try {
            response = duke.getResponse(input);
            dukeResponseImage = dukeImage;
        } catch (DukeException error) {
            response = error.getMessage();
            dukeResponseImage = dukeErrorImage;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeResponseImage)
        );
        userInput.clear();
    }
}
