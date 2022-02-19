package wonka.gui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import wonka.Wonka;

/**
 * Controller class for main window of Wonka chatbot. Provides layout for the other controls.
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

    private Stage stage;
    private Wonka wonka;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image wonkaImage = new Image(this.getClass().getResourceAsStream("/images/wonka.png"));

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        welcomeMessage();
    }

    public void setWonka(Wonka w) {
        wonka = w;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = wonka.getResponse(input) + "\n"
                + "________________________________\n" + wonka.handleInput(input)
                + "\n________________________________";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getWonkaDialog(response, wonkaImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            exitMessage();
            PauseTransition end = new PauseTransition(Duration.seconds(5));
            end.setOnFinished(x -> stage.close());
            end.play();

        }
    }

    @FXML
    private void welcomeMessage() {
        String welcomeMessage = "Woof! I'm Wonka!\nHow may I be of service?";
        dialogContainer.getChildren().add(DialogBox.getWonkaDialog(welcomeMessage, wonkaImage));
    }

    @FXML
    private void exitMessage() {
        String exitMessage = "Wonka is leaving in a few seconds...";
        dialogContainer.getChildren().add(DialogBox.getWonkaDialog(exitMessage, wonkaImage));
    }
}
