package lily.control;

import lily.Lily;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import lily.LilyException;

/**
 * Provides the layout for the other controls. Controller for MainWindow. 
 * @@author HanJiyao Referenced his delay function to show the exit message before quitting.
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
    @FXML
    private final Font NUNITO_MEDIUM = Font.loadFont(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/fonts/Nunito-Medium.ttf")), FONT_SIZE);
    @FXML
    private final Font NUNITO_BOLD = Font.loadFont(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/fonts/Nunito-Bold.ttf")), FONT_SIZE);

    private Lily lily;

    private static final int FONT_SIZE = 13;
    private static final int THINK_TIME_MILIS = 330;
    private static final int STICKER_TIME_MILIS = 1200;
    private static final int QUIT_TIME_MILIS = 2500;


    /**
     * Does post-processing after FXML attributes are fully loaded
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendButton.setStyle("-fx-background-color: #58cc02; -fx-text-fill: white; ");
    }

    public void setLily(Lily l) {
        // show welcome message
        LilyDialogBox welcomeMessage = LilyDialogBox.getDialog(l.getWelcome());
        think(THINK_TIME_MILIS, (event -> dialogContainer.getChildren().add(welcomeMessage)));
        lily = l;
    }

    /**
     * Creates two dialog boxes, one echoing user input 
     * and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        lily.readCommand(input);
        String response = lily.getResponse();

        // echo user's command
        dialogContainer.getChildren().add(UserDialogBox.getDialog(input));

        // display lily's response 
        LilyDialogBox respDialog = LilyDialogBox.getDialog(response);
        think(THINK_TIME_MILIS, (event -> dialogContainer.getChildren().add(respDialog)));

        userInput.clear();

        String keyword = (input.split(" "))[0];
        /**
         * @@author HanJiyao Referenced his delay function to show the exit message before quitting.
         */
        switch(keyword) {
        case "bye":
            LilyStickerBox party = LilyStickerBox.getSticker("party");
            think(STICKER_TIME_MILIS, (event -> dialogContainer.getChildren().add(party)));
            think(QUIT_TIME_MILIS, (event -> Lily.exitApplication()));
            break;

        case "mark":
        // Fallthrough
        case "do":
        // Fallthrough
        case "done":
            LilyStickerBox clap = LilyStickerBox.getSticker("clap");
            think(STICKER_TIME_MILIS, (event -> dialogContainer.getChildren().add(clap)));
            break;
        
        case "unmark":
        // Fallthrough
        case "delete":
        // Fallthrough
        case "remove":
            LilyStickerBox mock = LilyStickerBox.getSticker("mock");
            think(STICKER_TIME_MILIS, (event -> dialogContainer.getChildren().add(mock)));
            break;
        }
    }

    /**
     * Displays Lily saying the input.
     *
     * @param s Input for Lily to say.
     */
    @FXML
    public void display(String s) {
        dialogContainer.getChildren().add(LilyDialogBox.getDialog(s));
        userInput.clear();
    }
    
    private void think(int ms, EventHandler<ActionEvent> fn) {
        PauseTransition delay = new PauseTransition(Duration.millis(ms));
        delay.setOnFinished(fn);
        delay.play();
    }
}