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
        this.dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(startResponse.getMessage(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert this.duke != null : "The Duke instance should already be assigned";
        final String input = userInput.getText();
        final DukeResponse response = this.duke.processQuery(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response.getMessage(), dukeImage)
        );
        userInput.clear();

        if (response.isExit()) {
            final PauseTransition pause = new PauseTransition(Duration.ONE);
            pause.setOnFinished(e -> Platform.exit());
            pause.play();
        }
    }
}
