package dooke.ui;

import dooke.Dooke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

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

    private Dooke dooke;


    private Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));
    private Image dukeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/dooke.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDooke(Dooke d) {
        dooke = d;
        dialogContainer.getChildren().addAll(DialogBox.getDookeDialog(dooke.welcome(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    public void handleUserInput() {
        final String emptyString = "";
        String input = userInput.getText();
        if (input.equals(emptyString)) {
            return;
        }

        String response = dooke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDookeDialog(response, dukeImage)
        );
        userInput.clear();
        if (dooke.isBye(input)) {
            Platform.exit();
        }
    }
}