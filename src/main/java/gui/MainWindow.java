package gui;

import duke.Duke;
import duke.UI;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    /** Mike to be printed. */
    private Duke duke;

    /** Image of user. */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    /** Image of Mike. */
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets up the main window of Mike's GUI.
     *
     * @param d Instance of Mike object.
     */
    public void setDuke(Duke d) {
        duke = d;
        String intro = UI.printIntro();
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(intro, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Mike.
     * Mike's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        if (input.equals("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, this.userImage),
                    DialogBox.getDukeDialog(response, this.dukeImage)
            );
            userInput.setDisable(true);
            sendButton.setDisable(true);
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
            pauseTransition.setOnFinished(event -> System.exit(0));
            pauseTransition.play();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, this.userImage),
                    DialogBox.getDukeDialog(response, this.dukeImage)
            );
        }
        userInput.clear();
    }
}