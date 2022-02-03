package bernie.ui;

import bernie.Bernie;
import bernie.commands.CommandHandler;
import bernie.storage.Storage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private Button sendButton;

    private Bernie bernie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image bernieImage = new Image(this.getClass().getResourceAsStream("/images/DaBernie.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBernie(Bernie b) {
        bernie = b;
        CommandHandler commandHandler = b.getCommandHandler();
        InputResponder inputResponder = commandHandler.getInputResponder();
        Storage storage = commandHandler.getStorage();
        handleStartUp(inputResponder.greet(), storage.loadTasks(commandHandler));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bernie's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bernie.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBernieDialog(response, bernieImage)
        );
        userInput.clear();
        // Exit app if user inputs bye
        if (response == "See ya!") {
            Platform.exit();
        }
    }

    private void handleStartUp(String greetMsg, String loadMsg) {
        dialogContainer.getChildren().addAll(
                DialogBox.getBernieDialog(greetMsg, bernieImage)
        );
        dialogContainer.getChildren().addAll(
                DialogBox.getBernieDialog(loadMsg, bernieImage)
        );
    }
}
