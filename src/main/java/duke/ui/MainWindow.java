package duke.ui;

import duke.duke.Duke;

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

    private Duke duke;

    //setting the images
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/human.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/bear.jpg"));

    /**
     * Initialises scrollpane
     */
    @FXML
    public void initialize() {
        String dukeGreeting = "Hello! I'm Duke ^^ \nWhat can I do for you?";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(dukeGreeting, dukeImage));
    }

    /**
     * Sets duke
     *
     * @param d Duke object passed in
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String endMessage = "Bye. Hope to see you again soon!";
        String sleepErrorMessage = "Issue shutting down Duke! Force shutting down...";

        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));

        if (response.equals(endMessage)) {
            Platform.exit();
        }

        userInput.clear();
    }
}