package dazz.gui;

import dazz.Dazz;
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

    private Dazz dazz;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dazzImage = new Image(this.getClass().getResourceAsStream("/images/DaDazz.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDazz(Dazz dazz) {
        this.dazz = dazz;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Dazz's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.startsWith("bye")) {
            userInput.setEditable(false);
            userInput.setStyle("-fx-background-color: #e6e6e6");
            userInput.setMouseTransparent(true);
            userInput.setFocusTraversable(false);
            sendButton.setText("Exit");
            sendButton.setDisable(true);
        }
        String response = dazz.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDazzDialog(response, dazzImage)
        );
        userInput.clear();
    }

}
