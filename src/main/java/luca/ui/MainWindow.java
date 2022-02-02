package luca.ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import luca.Luca;

/**
 * Controller for MainWindow.
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

    /** Chat bot used for the logic. */
    private Luca luca;

    /** Image used to represent user. */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));

    /** Image used to represent the chat bot. */
    private Image lucaImage = new Image(this.getClass().getResourceAsStream("/images/luca.jpg"));

    /**
     * Binds the scroll pane to the dialog container and inserts
     * dialog box with welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getLucaWelcome(lucaImage));
    }

    /**
     * Sets the Luca Chat bot.
     *
     * @param luca Luca chat bot running the logic.
     */
    public void setLuca(Luca luca) {
        this.luca = luca;
    }

    /**
     *  Creates two dialog box, one with user input text and other with
     *  chat bot response. Finally clears up the input text field.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = luca.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLucaDialog(response, lucaImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            PauseTransition exitTransition = new PauseTransition(Duration.seconds(1));
            exitTransition.setOnFinished(event -> Platform.exit());
            exitTransition.play();
        }
    }
}