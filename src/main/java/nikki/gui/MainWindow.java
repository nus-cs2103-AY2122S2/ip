package nikki.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nikki.Nikki;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final Font FONT = new Font("Arial", 17);

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Nikki nikki;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/peko.PNG"));
    private Image nikkiImage = new Image(this.getClass().getResourceAsStream("/images/fbk.JPG"));

    /**
     * Initializes window GUI settings
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setFont(FONT);
        sendButton.setFont(FONT);
    }

    /**
     * Sets the Nikki object for this window instance
     * @param nikki Nikki object to interact with
     */
    public void setNikki(Nikki nikki) {
        this.nikki = nikki;
        nikkiSpeak(nikki.getIntroduction());
    }

    /**
     * Adds the message in a user dialog box
     * @param message message from user
     */
    private void userSpeak(String message) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(message, userImage));
    }

    /**
     * Adds the message in a Nikki dialog box
     * @param message message from Nikki
     */
    private void nikkiSpeak(String message) {
        dialogContainer.getChildren().add(DialogBox.getNikkiDialog(message, nikkiImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        userSpeak(input);

        String response = nikki.interact(input);
        nikkiSpeak(response);

        userInput.clear();

        if (nikki.isStopped()) {
            closeWindow();
        }
    }

    /**
     * Closes the MainWindow and exits the program.
     */
    private void closeWindow() {
        // Gets the handle to the window object
        Stage stage = (Stage) this.userInput.getScene().getWindow();
        stage.close();
        System.exit(0);
    }
}
