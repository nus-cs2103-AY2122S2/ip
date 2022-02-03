package duke.UI;

import java.io.IOException;

import duke.Duke;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user-dp.png"));
    private Image pikachuImage = new Image(this.getClass().getResourceAsStream("/images/pikachu-dp.png"));

    /**
     * Initializes the Pikachu chatbot, printing greeting message and setting certain styling properties.
     */
    @FXML
    public void initialize() {
        dialogContainer.getChildren().add(DialogBox.getPikachuDialog(
                UI.printGreeting() + "\n", pikachuImage));

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) throws IOException {
        d.getStorage().readTaskList();
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();

        //"bye" command
        if (input.toLowerCase().equals("bye")) {
            dialogContainer.getChildren().add(DialogBox.getPikachuDialog(
                    UI.printGoodbye() + "\n", pikachuImage));

            //Gives time for user to see goodbye message
            Thread.sleep(8);
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.play();

            exitApplication();
            return;
        }

        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPikachuDialog(response, pikachuImage)
        );
        userInput.clear();
    }

    @FXML
    public void exitApplication() {
        Platform.exit();
    }
}