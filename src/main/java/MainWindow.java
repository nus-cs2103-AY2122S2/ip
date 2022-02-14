import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tasks.Task;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final String greetingMessage = "Hello. I am HAL 4500.";
    private static final String offerHelpMessage = "Type 'help' for a list of possible commands.";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Hal hal;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/AstronautImage.png"));
    private Image halImage = new Image(this.getClass().getResourceAsStream("/images/CameraImage.png"));
    private Image halIcon = new Image(this.getClass().getResourceAsStream("/images/Hal9000Image.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setHal(Hal h) {
        hal = h;
    }

    /**
     * Displays some greeting messages.
     */
    public void displayGreeting() {
        dialogContainer.getChildren().add(DialogBox.getReturnDialog(greetingMessage, halImage));
        dialogContainer.getChildren().add(DialogBox.getReturnDialog(offerHelpMessage, halImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("")) {
            return;
        }
        String response = hal.getResponse(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        if (hal.handledError()) {
            dialogContainer.getChildren().add(DialogBox.getErrorDialogue(response, halImage));
        } else {
            dialogContainer.getChildren().add(DialogBox.getReturnDialog(response, halImage));
        }
        userInput.clear();
    }

    /**
     * Takes in the Stage, and sets any other settings before showing the stage.
     *
     * @param stage The Stage to be shown.
     * @return The Stage with edited settings.
     */
    public Stage changeSettings(Stage stage) {
        stage.setTitle("HAL 4500");
        stage.setResizable(false);
        stage.getIcons().add(halIcon);
        return stage;
    }
}
