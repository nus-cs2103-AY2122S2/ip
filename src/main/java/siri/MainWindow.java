package siri;

import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    /**
     * Creates the Object to be manipulated in FXML.
     */
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
        boolean toQuit = false;
        if (siri.checkInitialised() == false) {
            dialogContainer.getChildren().add(
                    DialogBox.setStartUpMessage(siri.getStartUpString(), siriImage)
            );
            siri.setInitialised();
        }
        String input = userInput.getText();
        String response;

        try {
            response = siri.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog("You:\n" + input, userImage),
                    DialogBox.getSiriDialog("Siri:\n" + response, siriImage)
            );
            userInput.clear();
        } catch (SiriException se) {
            response = se.getMessage();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog("You:\n" + input, userImage),
                    DialogBox.getWarningDialog(response)
            );
            userInput.clear();
        }

        if (response == "Bye!!") {
            response = this.siri.exitApp();
            toQuit = true;
        }

        if (toQuit == true) {
            try {
                TimeUnit.SECONDS.sleep(2);
                Platform.exit();
                System.exit(0);
            } catch (InterruptedException ie) {
                Platform.exit();
                System.exit(0);
            }
        }

    }
}
