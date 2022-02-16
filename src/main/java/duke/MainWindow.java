package duke;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/ape285.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/ape288.png"));

    /**
     * Initialise Main window with welcome message and proper height
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendDukeWelcomeMsg();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Sends a message to user by duke
     *
     * @param message Message to be sent to user by duke
     */
    private void sendDukeMsg(String message) {
        assert (message != null) : "message is null :(";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(message, dukeImage)
        );
    }

    /**
     * Display text/message of user as well as response by duke
     *
     * @param dukeText String response by duke
     * @param userText String input by user
     */
    private void sendDukeAndUserMsg(String dukeText, String userText) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(dukeText, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Sends duke welcome message
     */
    private void sendDukeWelcomeMsg() {
        sendDukeMsg(Ui.displayWelcomeMsg());
    }

    /**
     * Prints user input & profile picture + duke output & profile
     * picture on the GUI
     *
     * @throws DukeException
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();

        try {
            String dukeText = duke.run(userText);
            sendDukeAndUserMsg(dukeText, userText);

            if (duke.getIsExit()) {
                exitApplication(3000);
            }

        } catch (DukeException err) {
            err.printStackTrace();
        }
    }

    /**
     * Exit application with a 3s delay
     */
    private void exitApplication(int delayInMillis) {
        new Thread(() -> {
            try {
                Thread.sleep(delayInMillis);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            Platform.exit();
        }).start();
    }
}
