package ultoi.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ultoi.util.Ultoi;
import ultoi.util.UltoiException;

/**
 * Controls the main application window.
 *
 * @author snoidetx
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

    private Ultoi ultoi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image ultoiImage = new Image(this.getClass().getResourceAsStream("/images/DaUltoi.png"));

    /**
     * Initializes a main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the driver for the whole graphic user interface.
     * This method also displays the welcome message from the driver.
     *
     * @param ultoi Driver for GUI.
     */
    public void setDriver(Ultoi ultoi) {
        this.ultoi = ultoi;
        String welcomeMsg = ultoi.showWelcomeMsg();
        String loadingMsg = ultoi.showLoadingStatus();
        dialogContainer.getChildren().addAll(
                DialogBox.getUltoiDialog(welcomeMsg, ultoiImage),
                DialogBox.getUltoiDialog(loadingMsg, ultoiImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ultoi's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String ultoiText = ultoi.getResponse(userInput.getText());

        if (userText.equals("bye")) {
            Platform.exit();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage), (
                        !ultoiText.contains(UltoiException.EXCEPTION_FACE))
                        ? DialogBox.getUltoiDialog(ultoiText, ultoiImage)
                        : DialogBox.getUltoiErrorDialog(ultoiText, ultoiImage)
        );

        userInput.clear();
    }
}
