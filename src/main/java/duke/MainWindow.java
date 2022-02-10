package duke;

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
    private final Ui ui = new Ui();

    private Image bear = new Image(this.getClass().getResourceAsStream("/images/original.png"));
    private Image penguin = new Image(this.getClass().getResourceAsStream("/images/tonkatsu.png"));

    /**
     * Creates dialog box with welcome message shown
     */
    @FXML
    public void initialize() {

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeText = ui.showWelcomeMessage();
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(welcomeText, penguin));
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws DukeException {

        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, bear),
                DialogBox.getDukeDialog(response, penguin)
        );
        userInput.clear();
    }
}
