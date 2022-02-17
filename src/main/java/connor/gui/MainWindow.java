package connor.gui;

import java.io.IOException;

import connor.Connor;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private Pane background;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Connor connor;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserAvatar.png"));
    private Image connorImage = new Image(this.getClass().getResourceAsStream("/images/ConnorAvatar.png"));
    private Image mountainImage = new Image(this.getClass().getResourceAsStream("/images/Mountain.png"));

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
        setBackgroundBackground();
    }

    private void setBackgroundBackground() {
        double width = this.getWidth();
        double height = this.getHeight();
        BackgroundImage bi = new BackgroundImage(
                mountainImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(width, height, false, false, true, true));
        background.setBackground(new Background(bi));
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
        if (input.isBlank()) {
            userInput.clear();
            return;
        }
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
        this.userInput.setPromptText("Goodbye!");
        this.userInput.setDisable(true);
        this.sendButton.setDisable(true);
        PauseTransition pt = new PauseTransition(new Duration(2000));
        pt.setOnFinished(a -> javafx.application.Platform.exit());
        pt.play();
    }


}
