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
    private static final String greet = "Hello! I'm Mum!\nWhat can I do for you?\n"
            + "Type \"commands\" to get a list of all commands.\n";
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
     * Starts the JavaFX XML.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcome();
    }

    /**
     * Sets this duke class into the variable duke.
     *
     * @param d the specific duke being used
     */
    public void setDuke(Duke d) {
        duke = d;
    }
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input) + "\n" + duke.handleUserInput(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
    /**
     * Greet the user when bot launches.
     */
    @FXML
    private void showWelcome() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greet, dukeImage));
    }
}
