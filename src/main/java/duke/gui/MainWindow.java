package duke.gui;

import duke.Duke;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/soccat.jpeg"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/bear.png"));

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greet = "Hello I am DDX\n" + "What can I do for you?\n";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greet, dukeImage));
    }

    /**
     * Sets duke.
     *
     * @param d the d
     */
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
        String response = duke.getResponse(input);
        DialogBox userBox = DialogBox.getUserDialog(input, userImage);
        DialogBox dukeBox;
        if (duke.isError(input)) {
            dukeBox = DialogBox.getWarningDialog(response, dukeImage);
        } else {
            dukeBox = DialogBox.getDukeDialog(response, dukeImage);
        }
        dialogContainer.getChildren().addAll(
                userBox, dukeBox
        );
        userInput.clear();
        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
