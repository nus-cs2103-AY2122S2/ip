package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.Duke;


/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    protected Button sendButton;
    @FXML
    protected TextField userInput;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    private Duke duke;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/popcat.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Lul.jpg"));
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    protected void displayGreeting() {
        String greetingMessage = "Good day Sir. My name is Dook. \nHow may I be of assistance?";
        String dukeText = greetingMessage;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, dukeImage)
        );
    }

    /**
     * Displays the String containing the bot's reply to loading data
     */
    public void displayLoadMessage() {
        String responseMessage = duke.getLoadDataResponse();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(responseMessage, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    protected void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = duke.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(dukeText, dukeImage)
        );
        userInput.clear();
    }
}
