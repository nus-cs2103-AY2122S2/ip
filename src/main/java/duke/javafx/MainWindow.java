package duke.javafx;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/GongYoo.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/iu.png"));

    /**
     * Initialise ChatBot with greetings in the dialog box.
     */
    @FXML
    public void initialize() {
        String hello = ("Hello! Welcome, I'm Duke" + "\n" + "What can I do for you?");
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(hello, dukeImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
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
        if (input.trim().equals("bye")) {
            PauseTransition exitLag = new PauseTransition(Duration.seconds(2));
            exitLag.setOnFinished(event -> Platform.exit());
            exitLag.play();
        }
    }
}
