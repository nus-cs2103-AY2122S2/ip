package spike.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import spike.Spike;
import spike.command.ExitCommand;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String GREETING_OLD_USER =
            "Welcome back! Enter 'list' command to see your task list.";
    private static final String GREETING_NEW_USER =
            "Hello! I am Spike ⊂( ・ ̫・)⊃ Nice to meet you!\nWhat can I do for you?";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Spike spike;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image spikeImage = new Image(this.getClass().getResourceAsStream("/images/spike.png"));

    /**
     * Initializes the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    private void greetUser() {
        String greetMsg;
        if (spike.getNumOfTasks() == 0) {
            greetMsg = GREETING_NEW_USER;
        } else {
            greetMsg = GREETING_OLD_USER;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getSpikeDialog(greetMsg, spikeImage)
        );
    }

    public void setSpike(Spike s) {
        spike = s;
        spike.loadFile();
        greetUser();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = spike.getResponseCommand(input);
        if (response.equals(ExitCommand.EXIT_MESSAGE)) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getSpikeDialog(response, spikeImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getSpikeDialog(response, spikeImage)
            );
        }
        userInput.clear();
    }
}
