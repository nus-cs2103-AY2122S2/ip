package connor.gui;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import connor.Connor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow.
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

    private Connor connor;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserAvatar.png"));
    private Image connorImage = new Image(this.getClass().getResourceAsStream("/images/ConnorAvatar.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        try {
            connor = new Connor(Connor.getFilePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String startup = connor.runGui();
        dialogContainer.getChildren().add(
                DialogBox.getConnorDialog(startup, connorImage)
        );
    }

    public void setConnor(Connor c) {
        this.connor = c;
    }

    /**
     * Handles user input by parsing what they typed.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = connor.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getConnorDialog(response, connorImage)
        );
        userInput.clear();
        if (response.equals(Connor.getGoodbye())) {
            exit();
        }
    }

    /**
     * Closes the program.
     */
    @FXML
    private void exit() {
        // Disable input, wait 1 second and then close the program.
        this.userInput.setDisable(true);
        this.sendButton.setDisable(true);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        javafx.application.Platform.exit();
    }


}
