package jeff.ui;

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
import jeff.main.Jeff;
import jeff.parser.Parser;

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

    private Jeff jeff;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image jeffImage = new Image(this.getClass().getResourceAsStream("/images/Jeff.png"));

    /**
     * Initialised the first message with a greeting.
     */
    @FXML
    public void initialize() {
        dialogContainer.getChildren().add(
                DialogBox.getJeffDialog("My name is Jeff, how may I help you?", jeffImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setJeff(Jeff jeff) {
        this.jeff = jeff;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jeff.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJeffDialog(response, jeffImage)
        );
        if (Parser.isExit()) {
            //@@author James_D-reused
            //Reused from https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
            // with minor modifications
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
            //@@author
        }
        userInput.clear();
    }
}
