package duke.ui;

import java.io.InputStream;
import java.util.function.Consumer;
import java.util.function.Function;

import duke.Duke;
import duke.exception.DukeIoException;

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

    private Consumer<String> inputHandler;
    private Image userImage;
    private Image botImage;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        try {
            userImage = loadAsset("/images/user.png");
            botImage = loadAsset("/images/bot.png");
        } catch (DukeIoException ex) {
            System.out.println("[UI Load Error] Could not load application image");
        }

    }

    public void setInputHandler(Consumer<String> inputHandler) {
        this.inputHandler = inputHandler;
    }

    private Image loadAsset(String assetPath) throws DukeIoException {
        InputStream resourceStream = MainWindow.class.getResourceAsStream(assetPath);
        if (resourceStream != null) {
            return new Image(resourceStream);
        }
        throw new DukeIoException("Failed to load UI asset image");
    }

    public void printBotMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getBotDialog(message, botImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        userInput.clear();

        this.inputHandler.accept(input);
    }
}