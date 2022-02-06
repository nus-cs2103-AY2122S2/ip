package chatbot.gui.controller;

import static javafx.application.Platform.exit;

import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import chatbot.ChatBot;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


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

    private ChatBot innkeeper;

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/traveller.jpg")));
    private final Image innkeeperImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/innkeeper.jpg")));
    private final Image backgroundImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/bg.jpg")));

    /**
     * Initialize MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setBackground(
                new Background(
                        Collections.singletonList(new BackgroundFill(
                                Color.BROWN,
                                new CornerRadii(0),
                                new Insets(0))),
                        Collections.singletonList(new BackgroundImage(
                                backgroundImage,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER,
                                new BackgroundSize(
                                        1.0,
                                        1.0,
                                        true,
                                        true,
                                        false,
                                        false
                                )
                        ))
                ));
    }

    public void setInnkeeper(ChatBot i) {
        innkeeper = i;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing innkeeper's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = innkeeper.getResponse(input);

        if (input.equals("clear")) {
            dialogContainer.getChildren().clear();
            userInput.clear();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getInnkeeperDialog(response, innkeeperImage)
            );
            userInput.clear();
            if (input.equals("bye")) {
                CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS).execute(() -> exit());
            }
        }
    }

    /**
     * Creates a dialog box on application start to welcome the user.
     */
    public void start(String text) {
        dialogContainer.getChildren().add(
                DialogBox.getStartPrompt(text, innkeeperImage)
        );
    }
}

