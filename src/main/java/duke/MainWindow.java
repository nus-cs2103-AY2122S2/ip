package duke;

import java.io.IOException;

import duke.exceptions.DukeExceptions;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private duke.Duke dukeLocal;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/bulbasaur.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/pikachu.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(duke.Duke d) {
        dukeLocal = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {

        String input = userInput.getText();
        String response = null;
        try {
            response = dukeLocal.getResponse(input);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DukeExceptions e) {
            e.printStackTrace();
        }
        dialogContainer.getChildren().addAll(
                duke.DialogBox.getUserDialog(input, userImage),
                duke.DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

    }


}
