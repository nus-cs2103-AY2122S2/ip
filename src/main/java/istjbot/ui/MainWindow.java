package istjbot.ui;

import istjbot.IstjBot;
import javafx.animation.PauseTransition;
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
 * This is where the main logic happens.
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

    private IstjBot istjBot = new IstjBot("data/tasks.txt");

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/istjuser.png"));
    private Image istjBotImage = new Image(this.getClass().getResourceAsStream("/images/istjbot.jpeg"));

    /**
     * Initializes MainWindow
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Welcome message needed
        dialogContainer.getChildren().addAll(
                DialogBox.getIstjBotDialog(Ui.showWelcome(), istjBotImage)
        );
        if (istjBot.existsConstructorError()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getIstjBotDialog(istjBot.showConstructorError(), istjBotImage)
            );
        }
    }

    public void setIstjBot(IstjBot b) {
        istjBot = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = istjBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getIstjBotDialog(response, istjBotImage)
        );
        userInput.clear();

        if (istjBot.shouldExitIstjBot()) {
            sendButton.setDisable(true);
            userInput.setDisable(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            // After delay is finished, execute this arrow function
            delay.setOnFinished(event -> javafx.application.Platform.exit());
            delay.play();
        }
    }
}
