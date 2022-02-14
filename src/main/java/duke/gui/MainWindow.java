package duke.gui;

import duke.utils.Duke;
import duke.utils.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
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
//@@author goel-a-reused
// This class has been adapted from https://se-education.org/guides/tutorials/javaFx.html
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/drake.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/kwest.png"));

    /**
     * Method to initialize
     * the main window
     */
    @FXML
    public void initialize() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.printHello(), dukeImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
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
        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> {
                Platform.exit();
                duke.saveListToDisk();
            });
            delay.play();
        }
    }
}
//@@author goel-a
