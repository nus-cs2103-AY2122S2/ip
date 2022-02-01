package duke.manager.ui;

import duke.Duke;
import duke.exception.DukeException;
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
public class MainWindow extends AnchorPane {
    private static final String INITIALISATION_MESSAGE = "Hello!\n"
            + "Here are my commands:\n"
            + "1. list (lists out all the tasks)\n"
            + "2. todo taskname (adds a to do task with the taskname tagged to it)\n"
            + "3. event taskname /at date (adds a event task with the taskname tagged to it"
            + ", date has to be in yyyy-mm-dd format)\n"
            + "4. deadline taskname /by date (adds a deadline task with the taskname tagged to it"
            + ", date has to be in yyyy-mm-dd format)\n"
            + "5. delete x (deletes the task by the task number shown when you list them out. x is a number)\n"
            + "6. mark x (marks the task, by the task number, as done. x is a number)\n"
            + "7. find word (lists out all the tasks that contain the word in their taskname)\n"
            + "8. bye (exits the program)";
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
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));




    /**
     * Initialises the MainWindow, with Duke's greeting message and possible loading errors.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(INITIALISATION_MESSAGE, dukeImage));
        PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
        delay.setOnFinished(event -> {
            try {
                duke.load();
            } catch (DukeException e) {
                dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(e.getMessage(), dukeImage));
                System.out.println(e.getMessage());
            }
        });
        delay.play();
    }

    /**
     * Assigns Duke to this MainWindow.
     *
     * @param d Duke.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            //Solution below adapted from
            //https://stackoverflow.com/questions/30543619/how-to-use-pausetransition-method-in-javafx
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> Platform.exit());
            pause.play();
        }
    }

    @FXML
    private void handleLoadError(String errorMessage) {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(errorMessage, dukeImage));
    }
}
