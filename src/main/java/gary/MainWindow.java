package gary;

import gary.gui.DialogBox;

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

    private Gary gary;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/user.png"));
    private Image garyImage = new Image(this.getClass().getResourceAsStream("/gary2.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set Gary from Main class
     *
     * @param d Gary to be used
     */
    public void setGary(Gary d) {
        gary = d;
        dialogContainer.getChildren().add(
                DialogBox.getWelcomeMessage(garyImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String garyText = gary.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getGaryDialog(garyText, garyImage)
        );
        userInput.clear();
    }
}
