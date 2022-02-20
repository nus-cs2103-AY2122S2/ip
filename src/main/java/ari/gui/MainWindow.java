package ari.gui;

import ari.Ari;
import ari.command.ByeCommand;
import ari.ui.Ui;
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

    private Ari ari;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image ariImage = new Image(this.getClass().getResourceAsStream("/images/ari.png"));

    /**
     * Initializes MainWindow
     */
    @FXML
    public void initialize() {
        dialogContainer.getChildren().add(
                DialogBox.getAriDialog(Ui.displayWelcomeMessage(), ariImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAri(Ari a) {
        ari = a;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ari.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAriDialog(response, ariImage)
        );
        userInput.clear();

        // if response is a BYE_MESSAGE, give a bit of delay before closing the window
        if (response.equals(ByeCommand.BYE_MESSAGE)) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> {
                Platform.exit();
                System.exit(0);
            });
            delay.play();
        }
    }
}
