package tsundere;

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

    private Tsundere tsundere;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image tsundereImage = new Image(this.getClass().getResourceAsStream("/images/Tsundere1.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getTsundereDialog("Hmph, it's you again...", tsundereImage, false));
    }

    public void setTsundere(Tsundere d) {
        tsundere = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tsundere.getResponse(input);
        String[] lines = response.split("\r\n|\r|\n");
        boolean isChain = false;
        if (lines.length > 3) {
            dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
            for (int i = 0; i < lines.length; i+=3) {
                String s = lines[i];
                if (i + 1 < lines.length) {
                    s = s + "\n" + lines[i + 1];
                }
                if (i + 2 < lines.length) {
                    s = s + "\n" + lines[i + 2];
                }
                dialogContainer.getChildren().add(DialogBox.getTsundereDialog(s, tsundereImage, isChain));
                if (!isChain) {
                    isChain = true;
                }
            }

        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getTsundereDialog(response, tsundereImage, false)
            );
        }
        userInput.clear();
    }
}