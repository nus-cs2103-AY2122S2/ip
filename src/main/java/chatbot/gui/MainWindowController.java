package chatbot.gui;

import chatbot.command.CommandOutput;
import chatbot.util.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

class UserDialogBox extends DialogBox {
    public UserDialogBox(String message) {
        super("/view/fxml/UserDialogBox.fxml", "/view/png/user.png", message);
    }
}

class BotDialogBox extends DialogBox {
    public BotDialogBox(String message) {
        super("/view/fxml/BotDialogBox.fxml", "/view/png/bot.png", message);
    }
}

public class MainWindowController {
    public static final String NOTIFICATION_SOUND_FILE = "/audio/wav/notification.wav";
    private final MainWindowModel model;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Runnable terminateCallback;

    public MainWindowController() {
        // Since MainWindow is complicated, we split it into MVC.
        model = new MainWindowModel();
    }

    public void setTerminateCallback(Runnable terminateCallback) {
        this.terminateCallback = terminateCallback;
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(new BotDialogBox(
                "Hello! I'm Delphine. How may I help you today?\nType \"help\" for a list of commands."));
        Ui.playSound(NOTIFICATION_SOUND_FILE);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String text = userInput.getText();
        if (text.isBlank()) {
            return;
        }
        dialogContainer.getChildren().addAll(new UserDialogBox(text));
        Ui.playSound(NOTIFICATION_SOUND_FILE);
        CommandOutput output = model.getCommandList().executeCommand(text, model.getTaskList());
        dialogContainer.getChildren().addAll(new BotDialogBox(output.output));
        Ui.playSound(output.sfxFile);
        userInput.clear();

        if (output.terminate && terminateCallback != null) {
            terminateCallback.run();
        }
    }
}