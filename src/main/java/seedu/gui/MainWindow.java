package seedu.gui;

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
import seedu.commands.Command;
import seedu.duke.Duke;

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
    private PauseTransition delay;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/man.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));

    /**
     * Initialises the gui
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        delay = new PauseTransition(Duration.seconds(1));
    }

    /**
     * Initialises the duke class
     *
     * @param d A duke object
     */
    public void setDuke(Duke d) {
        duke = d;
        String welcomeMessage = "Welcome to Duke Bot!\nYour save file path is: " + duke.getPath();
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(welcomeMessage, dukeImage));
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


        //@@author BrendonLau-reused
        //Reused from https://github.com/BrendonLau/ip/blob/master/src/main/java/duke/MainWindow.java
        if (Command.isExit()) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
