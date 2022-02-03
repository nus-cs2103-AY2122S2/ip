package luke.ui;

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
import luke.Luke;
import luke.commands.Result;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final String GREETING_MESSAGE = "Hello! I am Luke! \nHow can I help you?";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Luke luke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DarthVader.png"));
    private Image lukeImage = new Image(this.getClass().getResourceAsStream("/images/Luke.png"));

    /**
     * Initializes the GUI and send out a greeting message from luke.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getLukeDialog(GREETING_MESSAGE, lukeImage));
    }

    public void setLuke(Luke l) {
        luke = l;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Luke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Result result = luke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLukeDialog(result.getResult(), lukeImage)
        );
        userInput.clear();
        if (result.isExit()) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
