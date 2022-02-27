package gui;

import walle.Walle;
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

    private Walle walle;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/captain.png"));
    private Image walleImage = new Image(this.getClass().getResourceAsStream("/images/walle.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        if (firstBootUp()) {
            showWelcome();
        }
    }

    public void setDuke(Walle d) {
        walle = d;
    }

    public boolean firstBootUp() {
        if (!walle.hasJustBooted) {
            return false;
        }
        walle.hasJustBooted = false;
        return true;
    }

    public void showWelcome() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(Ui.printStartup(), walleImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Walle's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = walle.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, walleImage)
        );
        userInput.clear();
    }
}