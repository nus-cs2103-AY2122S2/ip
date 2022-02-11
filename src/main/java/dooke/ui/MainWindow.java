package dooke.ui;

import java.util.Objects;

import dooke.Dooke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


/**
 * Controller for MainWindow.
 * Provides the layout for the other controls.
 * @author s7manth
 * @version 0.2
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

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/user.png")));
    private final Image dookeImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/dooke.png")));

    /**
     * The initializer method to load the fxml components.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Method for Dooke's initial setup.
     * @param dooke Instance of Dooke.
     */
    public void setDooke(Dooke dooke) {
        this.dooke = dooke;
        dialogContainer.getChildren().addAll(DialogBox.getDookeDialog(this.dooke.welcome(), dookeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Dooke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
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
                DialogBox.getDookeDialog(response, dookeImage)
        );
        userInput.clear();
        if (dooke.isBye(input)) {
            Platform.exit();
        }
    }
}
