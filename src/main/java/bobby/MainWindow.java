package bobby;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private Button sendButton;

    private Bobby bobby;

    private final Image USER_IMAGE = new Image(this.getClass().getResourceAsStream("/images/Stickman.png"));
    private final Image BOBBY_IMAGE = new Image(this.getClass().getResourceAsStream("/images/Bobby.png"));
    private final ImageView USER_IV = new ImageView(USER_IMAGE);
    private final ImageView BOBBY_IV = new ImageView(BOBBY_IMAGE);

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(new Label(Ui.showWelcome()), BOBBY_IV));
    }

    public void setBobby(Bobby b) {
        bobby = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userInputString = userInput.getText();
        Label userText = new Label(userInputString);
        Label dukeText = new Label(bobby.getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, USER_IV),
                DialogBox.getDukeDialog(dukeText, BOBBY_IV)
        );
        if (userInputString.equals("bye")) {
            Platform.exit();
        }

        userInput.clear();
    }
}