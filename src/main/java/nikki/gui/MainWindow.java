package nikki.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import nikki.Nikki;

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

    private final Font FONT = new Font("Arial", 18);

    private Nikki nikki;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/peko.PNG"));
    private Image nikkiImage = new Image(this.getClass().getResourceAsStream("/images/fbk.JPG"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setFont(FONT);
        sendButton.setFont(FONT);
    }

    public void setNikki(Nikki d) {
        nikki = d;
        nikkiSpeak(nikki.getIntroduction());
    }

    private void userSpeak(String message) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(message, userImage));
    }

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
    }
}