package dukelauncher;

import java.util.Timer;
import java.util.TimerTask;

import duke.Duke;
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
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserAvatar.png"));
    private final Image dukeImage = new Image(
        this.getClass().getResourceAsStream("/images/WensleydaleAvatar.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;

    /**
     * Initializes the GUI variable.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        //welcome message
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog("Why hello there! My name is Wensleydale.\nWhat do you need?", dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );

        if (input.equalsIgnoreCase("bye")) {
            userInput.setDisable(true); //disables these features to prevent further user input
            sendButton.setDisable(true);
            new Timer().schedule(new TimerTask() {
                public void run() {
                    System.exit(0);
                }
            }, 3000);
        }
        userInput.clear();
    }
}
