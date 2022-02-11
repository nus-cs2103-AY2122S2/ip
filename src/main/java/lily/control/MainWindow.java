package lily.control;

import lily.Lily;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lily.LilyException;

import java.util.Objects;

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

    private Lily lily;

    private final Image USER_IMAGE = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/lilyugh.png")));
    private final Image LILY_IMAGE = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/lily.png")));

    /**
     * Does post-processing after FXML attributes are fully loaded
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setLily(Lily l) {
        lily = l;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        lily.readCommand(input);
        String response = lily.getResponse();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getLilyDialog(response, LILY_IMAGE)
        );

        userInput.clear();

        if (input.equals("bye")) {
            Lily.exitApplication();
            /*
            Please don't mark me down for dead code, this is for future features.
            Enable this if you can get the close message in UI to work
            try {
                Thread.sleep(3000); // apparently this pauses the printing
            } catch (InterruptedException ie) {
                lily.displayError(LilyException.ERROR_QUIT_INTERRUPT);
            } finally {
                Lily.exitApplication();
            }
             */
        }
    }

    /**
     * Displays Lily saying the input.
     *
     * @param s Input for Lily to say.
     */
    @FXML
    public void display(String s) {
        dialogContainer.getChildren().add(DialogBox.getLilyDialog(s, LILY_IMAGE));
        userInput.clear();
    }
}