package duke.ui;

import java.util.Objects;

import duke.Duke;
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
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String USER_IMAGE = "/images/DaUser.png";
    private static final String DUKE_IMAGE = "/images/DaDuke.png";
    private static final String DUKE_ERROR_IMAGE = "/images/DaDukeError.png";

    private static final int CLOSE_APPLICATION_TIME = 1;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

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

        //start greeting
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(Duke.GREETING, dukeImage));
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

        if (duke.isEndDukeCommand(input)) {
            closeApplication();
        }
    }

    /**
     * Closes the application after a set amount of time.
     */
    public void closeApplication() {
        PauseTransition pause = new PauseTransition(Duration.seconds(CLOSE_APPLICATION_TIME));
        pause.setOnFinished(event -> Platform.exit());
        pause.play();

        userInput.setDisable(true);
    }
}
