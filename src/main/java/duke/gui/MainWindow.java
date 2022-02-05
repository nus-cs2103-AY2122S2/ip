package duke.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller class for main window of Duke chatbot. Provides layout for the other controls.
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

    private duke.Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/wonka.png"));

    @FXML
    private void initialize() {
        welcomeMessage();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(duke.Duke d) {
        duke = d;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input) + "\n"
                + "________________________________\n" + duke.handleInput(input)
                + "\n________________________________";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    @FXML
    private void welcomeMessage() {
        String welcomeMessage = "Woof! I'm Wonka!\nHow may I be of service?";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, dukeImage));
    }
}
