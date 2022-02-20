import duke.Command;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import duke.Duke;
import javafx.scene.paint.Paint;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserPic3.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/BMO2.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        String startMsg = duke.startDuke();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(startMsg, dukeImage)
        );
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
            duke.saveDuke();
            waitAndThenExit();
        }

    }

    /**
     * Waits for a moment and then exit the program
     */
    private void waitAndThenExit() {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException exc) {
                System.out.println("Exit operation interrupted");
                System.out.println(exc);
            }

            Platform.exit();
        }).start();
    }
}