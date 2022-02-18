package echo.ui;

import java.util.Objects;

import echo.Echo;
import echo.parser.Parser;
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

    private Echo echo;

    private Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/User.png")));
    private Image echoImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Echo.png")));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets echo.
     *
     * @param e Echo
     */
    public void setEcho(Echo e) {
        echo = e;
    }

    /**
     * Creates a dialog container with the opening message.
     */
    @FXML
    public void greet() {
        dialogContainer.getChildren().addAll(
                DialogBox.getEchoDialog(echo.getOpeningMessage(), echoImage)
        );
    }

    /**
     * Creates a dialog container with the status of loading data file.
     */
    @FXML
    public void loadFile() {
        dialogContainer.getChildren().addAll(
                DialogBox.getEchoDialog(echo.loadFile(), echoImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = echo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEchoDialog(response, echoImage)
        );
        userInput.clear();
        if (Parser.isExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
