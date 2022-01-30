package duke.main;

import java.io.IOException;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaJamie.png"));


    /**
     * Returns nothing, but used for initializing the startUp process of GUI.
     */
    @FXML
    public void initialize() {
        String greeting = "Hello! I'm TaskJamie\nWhat can i do for you?";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greeting, dukeImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Returns nothing, but used for assigning duke object & its respective storage.
     * @param duke duke object created from the Main class.
     */
    public void setDuke(Duke duke) {
        String home = System.getProperty("user.home");
        this.duke = duke;
        try {
            duke.initializeStorage(home, "/data/TaskData.txt");
        } catch (IOException e) {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(e.getMessage(), dukeImage));
        }
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
