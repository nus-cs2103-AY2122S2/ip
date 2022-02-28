package duke;

import java.util.Objects;

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
    @FXML
    private Button helpButton;

    private Duke duke;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/CS2103T.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/Damith.jpeg")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greet = "Hello, Prof here.\n"
                + "What can I do for you?\n";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greet, dukeImage));
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
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (Objects.equals(input, "bye")) {
            //@@author ddx-510-reused
            PauseTransition wait = new PauseTransition(Duration.seconds(3));
            wait.setOnFinished(event -> Platform.exit());
            wait.play();
            //@@author
        }
    }

    /**
     * Creates one dialogue box for Prof. He will return a text box that details oll the commands in Prof.
     */
    @FXML
    private void handleHelpInput() {
        String response = duke.getHelp();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage)
        );
    }
}
