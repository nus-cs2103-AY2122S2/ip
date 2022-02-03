package duke.main;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaHappy.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaPenguin.png"));

    /**
     * Initialises the scrollPane. This is the main window of the entire application.
     * It also calls onStartup(), which will show the welcome text.
     */
    @FXML
    public void initialize() {
        // initalize is called on startup
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.onStartup();
    }

    /**
     * Shows welcome text onto the window upon startup
     */
    public void onStartup() {
        Ui.showWelcome();
        String response = Ui.getDukeResponse();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
