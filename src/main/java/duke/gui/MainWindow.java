package duke.gui;

import duke.Duke;
import duke.Expression;
import duke.Response;
import duke.Ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * Images used in this application are sourced from sticker packs from
 * https://getstickerpack.com/stickers/genshin-impact-aether and
 * https://getstickerpack.com/stickers/genshin-impact-paimon-1 by U-KEY.
 *
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
    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DukeDefault.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response r = duke.getResponse(input);

        String response = r.getMessage();
        Expression expression = r.getExpression();

        dukeImage = getDukeImage(expression);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (r.isExit()) {
            PauseTransition end = new PauseTransition(Duration.seconds(1));
            end.setOnFinished(a -> endProgram());
            end.play();
        }
    }

    /**
     * Exit program.
     */
    private void endProgram() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Displays welcome message.
     */
    private void showWelcomeMessage() {
        String response = Ui.showWelcome();

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Gets Duke's image depending on its expression.
     * Idea is adapted from kev-intq(@github)'s GUI.
     *
     * @param expression Duke's expression
     * @return image with expression.
     */
    private Image getDukeImage(Expression expression) {
        String filePath = "/images/";

        switch(expression) {
        case DISAPPOINTED:
            filePath += "DukeDisappointed.png";
            break;
        case HAPPY:
            filePath += "DukeHappy.png";
            break;
        case THUMBSUP:
            filePath += "DukeThumbsUp.png";
            break;
        default:
            filePath += "DukeDefault.png";
            break;
        }

        return new Image(this.getClass().getResourceAsStream(filePath));
    }
}
