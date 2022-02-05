package saitama.ui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import saitama.Saitama;
import saitama.exceptions.SaitamaException;

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

    private Saitama saitama;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/King.jpg")));
    private final Image saitamaImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Saitama.jpg")));
    private final Image saitamaExceptionImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream(
            "/images/SaitamaException.jpg")));
    private final Image initialSaitamaImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/InitialSaitama.jpg")));

    /**
     * Initialises the MainWindow.
     */
    @FXML
    public void initialize() {
        dialogContainer.getChildren().add(DialogBox.getSaitamaDialog(
                "Hello, I'm Saitama, a hero for fun.\nWhat can I do for you?", initialSaitamaImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setSaitama(Saitama d) {
        saitama = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Saitama's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = saitama.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getSaitamaDialog(response, saitamaImage));
        } catch (SaitamaException e) {
            String response = e.getMessage();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getSaitamaDialog(response, saitamaExceptionImage));
        }
        userInput.clear();
    }
}
