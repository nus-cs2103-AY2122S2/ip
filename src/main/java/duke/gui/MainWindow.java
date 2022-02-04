package duke.gui;

import duke.Duke;
import duke.exception.DukeException;
import duke.util.Ui;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    private Stage stage;
    private static final Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d, Stage stage) {
        duke = d;
        this.stage = stage;
        // Send welcome message
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(ui.showWelcome(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = duke.getResponse(input);
            if (response.equals("exit")) {
                handleExit(input);
                userInput.clear();
                return;
            }
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog("Adventurer said: " + input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        } catch (DukeException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog("Adventurer replies: " + input, userImage),
                    DialogBox.getDukeDialog(e.getMessage(), dukeImage)
            );
        }
        userInput.clear();
    }

    /**
     * Handles the termination of the chatbot
     */
    private void handleExit(String input) {
        String response = ui.showExitMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog("Adventurer said: " + input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.setDisable(true);
        sendButton.setText("Exit");
        sendButton.setOnAction(event -> stage.close());
    }

}

