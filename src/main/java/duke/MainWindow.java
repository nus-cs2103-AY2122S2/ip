package duke;

import javafx.fxml.FXML;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
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
    private DialogBox dukeWindow;
    private DialogBox userWindow;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/gude.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duckling.png"));
    private Image sadDukeImage = new Image(this.getClass().getResourceAsStream("/images/SadDuck.png"));

    private String welcome = "Hello! I'm Ducky! :)\n" + "I am a task manager.\n"
                + "Type 'help' for more information on the commands you can give me.\n"
                + "What can I do for you today?\n"
                + "✧･ﾟ: *✧･ﾟ:* |\\__( o)> *:･ﾟ✧*:･ﾟ✧";

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // @@LapisRaider punnyhuimin-reused
        // Reused from https://github.com/LapisRaider/ip/blob/master/src/main/java/duke/ui/MainWindow.java
        // Tried to set up the scroll pane to be transparent in the fxml but could not do so. Noticed that
        // this user managed to do so and the code snippet that could achieve that was the following.
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        //@@LapisRaider
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcome, dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    private void handleExit() {
        userInput.setDisable(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().toLowerCase(java.util.Locale.ROOT);
        String response = duke.getResponse(input);
        userWindow = DialogBox.getUserDialog(input, userImage);
        if (duke.isError()) {
            dukeWindow = DialogBox.getWarningDukeDialog(response, sadDukeImage);
        } else {
            dukeWindow = DialogBox.getDukeDialog(response, dukeImage);
        }
        if (input.equals("bye")) {
            handleExit();
        }
        dialogContainer.getChildren().addAll(
                userWindow,
                dukeWindow
        );
        userInput.clear();
    }
}