package duke;

import duke.commands.Command;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        printDukeOutputBuffer();
    }

    private void printDukeOutputBuffer() {
        while (!duke.isOutputBufferEmpty()) {
            String outputMessage = duke.pollNextResponse();
            dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(outputMessage, dukeImage));
        }
    }

    /**
     * Creates a dialog box echoing the user input and then appends it to the dialog
     * container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));
        duke.generateOutput(input);
        printDukeOutputBuffer();
        userInput.clear();

        if (input.equals(Command.BYE_COMMAND)) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> exit());
            pause.play();
        }
    }

    private void exit() {
        Platform.exit();
        System.exit(0);
    }
}
