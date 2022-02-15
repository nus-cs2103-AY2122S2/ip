package duke.javafx;

import duke.main.Duke;
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

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    /**
     * Initialize main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        assert userImage != null;
        assert dukeImage != null;
    }

    public void setDuke(Duke d) {
        duke = d;
        assert duke != null;
        String greeting = duke.getGreeting();
        DialogBox dbGreeting = DialogBox.getDukeDialog(greeting, dukeImage);
        dialogContainer.getChildren().addAll(dbGreeting);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        DialogBox dbUser = DialogBox.getUserDialog(input, userImage);
        DialogBox dbDuke = DialogBox.getDukeDialog(response, dukeImage);
        dialogContainer.getChildren().addAll(dbUser, dbDuke);
        userInput.clear();
    }
}
