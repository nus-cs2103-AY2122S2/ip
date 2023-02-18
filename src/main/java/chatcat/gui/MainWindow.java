package chatcat.gui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import chatcat.ChatCat;
import javafx.util.Duration;

/**
 * Code for GUI was referenced from se-education.org
 * https://se-education.org/guides/tutorials/javaFx.html
 */

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

    private ChatCat chatcat;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image chatCatImage = new Image(this.getClass().getResourceAsStream("/images/chatCat.jpeg"));

    @FXML
    public void initialize()  {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setChatCat(ChatCat chatcat) {
        this.chatcat = chatcat;
        this.dialogContainer.getChildren().add(
            DialogBox.getChatCatDialog(chatcat.showWelcomeSMS(), chatCatImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (!input.isBlank()) {
            String response = chatcat.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getChatCatDialog(response, chatCatImage)
            );
        }

        if (input.equals("bye")) {
            PauseTransition wait = new PauseTransition(Duration.seconds(1));
            wait.setOnFinished(event -> {
                System.exit(0);
            });
            wait.play();
        }

        userInput.clear();
    }
}
