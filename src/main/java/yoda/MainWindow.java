package yoda;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

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

    private Yoda yoda;

    private Image jediImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/jediAvatar.png")));
    private Image yodaImage = new Image(this.getClass().getResourceAsStream("/images/yodaAvatar.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setYoda(Yoda y) {
        yoda = y;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Yoda's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = yoda.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, jediImage),
                DialogBox.getYodaDialog(response, yodaImage)
        );
        userInput.clear();
    }
}
