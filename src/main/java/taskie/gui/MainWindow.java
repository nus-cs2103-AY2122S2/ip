package taskie.gui;

import taskie.Taskie;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Taskie taskie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Taskie d) {
        taskie = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Taskie's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = taskie.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (!taskie.isListening) {
            new Thread(() -> exit(3000)).start();
        }
    }

    // Solution below adapted from https://stackoverflow.com/questions/24104313

    /**
     * Exits the program after a specified delay.
     *
     * @param ms The delay to wait before exiting the program in milliseconds.
     */
    private void exit(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Platform.exit();
    }

    /**
     * Greets the user with greet message.
     */
    @FXML
    public void greet() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(taskie.greet(), dukeImage)
        );
    }


}
