package ui;

import commands.StartCommand;
import duke.Duke;
import duke.DukeResponse;
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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/bot.jpg"));

    private final DialogBox botIsTypingDialog = DialogBox.getDukeDialog(". . .", this.dukeImage);

    /**
     * Sets up the main window.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke chatbot and greets the user.
     *
     * @param d the Duke chatbot that the user will interact with.
     */
    public void setDuke(Duke d) {
        this.duke = d;
        final DukeResponse startResponse = this.duke.processQuery(StartCommand.COMMAND);
        final DialogBox startDialog = DialogBox.getDukeDialog(startResponse.getMessage(), dukeImage);
        this.dialogContainer.getChildren().add(startDialog);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert this.duke != null : "The Duke instance should already be assigned";
        final String input = this.userInput.getText();
        final DukeResponse response = this.duke.processQuery(input);

        final DialogBox userDialog = DialogBox.getUserDialog(input, this.userImage);
        dialogContainer.getChildren().addAll(userDialog, this.botIsTypingDialog);
        userInput.clear();

        final PauseTransition botProcessingPause = new PauseTransition(Duration.seconds(0.65));
        botProcessingPause.setOnFinished(e -> {
            dialogContainer.getChildren().remove(this.botIsTypingDialog);
            final DialogBox botResponseDialog = DialogBox.getDukeDialog(response.getMessage(), this.dukeImage);
            dialogContainer.getChildren().add(botResponseDialog);
        });
        botProcessingPause.play();

        if (response.isExit()) {
            final PauseTransition pause = new PauseTransition(Duration.seconds(1.4));
            pause.setOnFinished(e -> Platform.exit());
            pause.play();
        }
    }
}
