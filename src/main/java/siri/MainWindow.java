package siri;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;


public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Siri siri;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image siriImage = new Image(this.getClass().getResourceAsStream("/images/DaSiri.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    public void setSiri(Siri s) {
        siri = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = siri.getResponse(input);
        if (response == "Bye!!") {
            response = this.siri.exitApp();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog("You:\n" + input, userImage),
                DialogBox.getSiriDialog("Siri:\n" + response, siriImage)
        );
        userInput.clear();
    }
}
