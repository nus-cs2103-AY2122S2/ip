package spark.ui;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import spark.Spark;
import spark.commandresponse.CommandResponse;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML // @FXML annotation marks a private or protected member and makes it accessible to FXML despite its modifier
    private ScrollPane dialogScrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button runButton;

    private Spark spark;

    private final Image sparkImage = new Image(this.getClass().getResourceAsStream("/images/343_guilty_spark.png"));
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/mister_chief.png"));

    @FXML
    public void initialize() {
        dialogScrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setSpark(Spark spark) {
        this.spark = spark;
        showInitialisationMessage();
    }

    private void showInitialisationMessage() {
        String welcomeMessage = "Greetings, erm, reclaimer...?" + "\n"
                + "(why does he look so weird?)";

        List<CommandResponse> listOfTasks = spark.executeCommand("list");
        addSparkChatBubble(welcomeMessage);
        addCommandResponsesAsChatBubbles(listOfTasks);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing Spark's reply and then appends them to the
     * dialog container.
     * <p>
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        List<CommandResponse> responses = spark.executeCommand(getUserInput());

        // print what the user typed as a chat-message sent by the user
        addUserChatBubble(getUserInput());

        // print each response messages resulting from the command as
        // chat-messages sent by Spark
        addCommandResponsesAsChatBubbles(responses);

        userInput.clear();

        // close the program if the user has given a command
        // for the program to exit
        if (programShouldExit(responses)) {
            System.exit(0);
        }
    }

    private void addUserChatBubble(String message) {
        dialogContainer
                .getChildren()
                .add(UserDialogBox.getDialog(message, userImage));
    }

    private void addSparkChatBubble(String message) {
        dialogContainer
                .getChildren()
                .add(SparkDialogBox.getDialog(message, sparkImage));
    }

    private void addCommandResponseAsChatBubble(CommandResponse response) {
        if (response.isError()) {
            dialogContainer
                    .getChildren()
                    .add(SparkDialogBox.getErrorSparkDialog(response.getMessage(), sparkImage));
        } else if (response.isWarning()) {
            dialogContainer
                    .getChildren()
                    .add(SparkDialogBox.getWarningSparkDialog(response.getMessage(), sparkImage));
        } else {
            dialogContainer
                    .getChildren()
                    .add(SparkDialogBox.getSuccessSparkDialog(response.getMessage(), sparkImage));
        }
    }

    private void addCommandResponsesAsChatBubbles(List<CommandResponse> responses) {
        for (CommandResponse r : responses) {
            addCommandResponseAsChatBubble(r);
        }
    }

    private boolean programShouldExit(List<CommandResponse> responses) {
        for (CommandResponse r : responses) {
            if (r.isExit()) {
                return true;
            }
        }
        return false;
    }

    private String getUserInput() {
        return userInput.getText();
    }
}
