package spike.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import spike.Spike;
import spike.command.Command;
import spike.command.IncorrectCommand;
import spike.command.RemindCommand;
import spike.task.TaskList;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
//Solution below adapted from https://se-education.org/guides/tutorials/javaFx.html
public class MainWindow extends AnchorPane {
    private static final String GREETING_OLD_USER =
            "Welcome back! Enter 'list' command to see your task list.";
    private static final String GREETING_NEW_USER =
            "Hello! I am Spike ⊂( ・ ̫・)⊃ Nice to meet you!\nWhat can I do for you?";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Spike spike;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image spikeImage = new Image(this.getClass().getResourceAsStream("/images/spike.png"));

    /**
     * Initializes the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    private void greetUser() {
        String greetMsg;
        if (spike.getNumOfTasks() == 0) {
            greetMsg = GREETING_NEW_USER;
        } else {
            greetMsg = GREETING_OLD_USER;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getSpikeDialog(greetMsg, spikeImage)
        );
    }
    private void remindUser(TaskList tasks) {
        Command remindCommand = new RemindCommand(1);
        String result = remindCommand.execute(tasks);
        if (!result.equals("")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getSpikeDialog(remindCommand.execute(tasks), spikeImage)
            );
        }
    }

    public void setSpike(Spike s) {
        spike = s;
        spike.loadFile();
        greetUser();
        remindUser(s.getTasks());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (isInputEmpty(input)) {
            userInput.clear();
            return;
        }
        Command responseCommand = spike.getResponseCommand(input);
        String response = responseCommand.execute(spike.getTasks());
        DialogBox user = DialogBox.getUserDialog(input, userImage);
        DialogBox bot = responseCommand instanceof IncorrectCommand
                        ? DialogBox.getErrorDialog(response, spikeImage)
                        : DialogBox.getSpikeDialog(response, spikeImage);
        dialogContainer.getChildren().addAll(user, bot);
        userInput.clear();
    }

    /**
     * Checks if the input consists of only space
     *
     * @param input String input
     * @return true if the input is not empty, else false
     */
    private boolean isInputEmpty(String input) {
        return input.trim().equals("");
    }
}
