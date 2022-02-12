package holobot.gui;

import holobot.HoloBot;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private HoloBot holoBot;

    // Image Credit to kelsoji (https://www.pinterest.com/kelsoji)
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Rushia.png"));
    private Image holoBotImage = new Image(this.getClass().getResourceAsStream("/images/Pekora.png"));

    /**
     * Initialises Holobot with a scrollPane.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Displays the welcome message.
     *
     * @param d The HoloBot object
     */
    public void setHoloBot(HoloBot d) {
        holoBot = d;
        dialogContainer.getChildren().addAll(DialogBox.getHoloBotDialog("Hi! I am HoloBot :D\n"
                + "How may I help you?", holoBotImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing HoloBot's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = holoBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getHoloBotDialog(response, holoBotImage)
        );
        userInput.clear();
        if (response.equals("See you again, peko!")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
