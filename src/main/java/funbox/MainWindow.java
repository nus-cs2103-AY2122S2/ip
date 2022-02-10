package funbox;

import funbox.util.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


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

    private FunBox funbox;

    private Stage stage;

    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        String greeting = ui.greetUser();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greeting, dukeImage)
        );
    }

    public void setFunBox(FunBox d) {
        funbox = d;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Result response = funbox.getResponse(input);

        if (!response.isExit()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response.getResponse(), dukeImage)
            );
            userInput.clear();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(response.getResponse(), dukeImage)
            );
            userInput.clear();
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> stage.close());
            delay.play();
        }
    }
}