package karen.gui;

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
import karen.Karen;
import karen.Main;
import karen.Ui;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * Adapted from: https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class MainWindow extends AnchorPane {
    public static final String USER_IMAGE_PATH = Main.USER_IMAGE_PATH;
    public static final String KAREN_IMAGE_PATH = Main.KAREN_IMAGE_PATH;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Karen karen;

    private Image userImage = new Image(this.getClass().getResourceAsStream(USER_IMAGE_PATH));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream(KAREN_IMAGE_PATH));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getKarenDialog(Ui.WELCOME, dukeImage)
        );
    }

    /**
     * Sets Karen object
     * @param karen Karen object associated with MainWindow used in GUI
     */
    public void setKaren(Karen karen) {
        this.karen = karen;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     *
     * Partial reference to:
     * https://stackoverflow.com/questions/30543619/how-to-use-pausetransition-method-in-javafx
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = this.karen.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKarenDialog(response, dukeImage)
        );
        userInput.clear();

        if (response.equals(Ui.GOODBYE)) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        }
    }
}

