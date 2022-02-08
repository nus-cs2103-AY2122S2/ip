package duke.ui;

import java.io.InputStream;
import java.util.function.Consumer;

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

    /** The handler that to be fired for input events. */
    private Consumer<String> inputHandler;
    /** Image resource file for the user's picture */
    private Image userImage;
    /** Image resource file for the bot's picture */
    private Image botImage;

    /**
     * Initializes the ScrollPane node and loads image assets required for the Ui.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        try {
            userImage = loadImageAsset("/images/user.png");
            botImage = loadImageAsset("/images/bot.png");
        } catch (DukeIoException ex) {
            System.out.println("[UI Load Error] Could not load application image");
        }

    }

    /**
     * Sets the handler that should be fired on input events.
     *
     * @param inputHandler The handler that should be fired on input events.
     */
    public void setInputHandler(Consumer<String> inputHandler) {
        this.inputHandler = inputHandler;
    }

    /**
     * Loads the image asset located at the supplied path as an <code>Image</code> object.
     *
     * @param assetPath Path of the image asset to load within the resources folder.
     * @return The <code>Image</code> object loaded from the supplied path.
     * @throws DukeIoException If the image at the supplied path cannot be loaded.
     */
    private Image loadImageAsset(String assetPath) throws DukeIoException {
        InputStream resourceStream = MainWindow.class.getResourceAsStream(assetPath);
        if (resourceStream != null) {
            return new Image(resourceStream);
        }
        throw new DukeIoException("Failed to load UI asset image");
    }

    /**
     * Prints a message as the bot in the Ui.
     *
     * @param message The message to be printed.
     */
    public void printBotMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getBotDialog(message, botImage));
    }

    /**
     * Creates a dialog box for the user input and clears the user input.
     * Fires and hands over the input to <code>inputHandler</code>, if it exists, to be processed.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        userInput.clear();

        if (this.inputHandler != null) {
            this.inputHandler.accept(input);
        }
    }
}
