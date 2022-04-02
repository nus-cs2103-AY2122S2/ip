package tsundere;

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

    private static final int TIMER = 1;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Tsundere tsundere;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private final Image tsundereImage = new Image(this.getClass().getResourceAsStream("/images/Tsundere1.jpg"));

    /**
     * Initializes with a DialogBox with intro.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getTsundereDialog("Hmph, it's you again...", tsundereImage));
    }

    /**
     * Sets the Tsundere property to input Tsundere.
     *
     * @param d input Tsundere.
     */
    public void setTsundere(Tsundere d) {
        tsundere = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tsundere.getResponse(input);
        assert userImage == null : "Error in the problem";
        assert tsundereImage == null : "Error in the problem";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTsundereDialog(response, tsundereImage)
        );
        userInput.clear();
        if (response == "Finally, you're leaving!\nIt's not like i will miss you or anything...") {
            PauseTransition delay = new PauseTransition(Duration.seconds(TIMER));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }

    }
}
