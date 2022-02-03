package chatbot.gui;

import chatbot.command.CommandOutput;
import chatbot.util.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

class UserDialogBox extends DialogBox {
    public UserDialogBox(String name, String message) {
        super(name, message,"/view/UserDialogBox.fxml");
    }
}

class BotDialogBox extends DialogBox {
    public BotDialogBox(String name, String message) {
        super(name, message,"/view/BotDialogBox.fxml");
    }
}

public class MainWindowController {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private MainWindowModel model;

    public MainWindowController() {
        // Since MainWindow is complicated, we split it into MVC.
        model = new MainWindowModel();
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String text = userInput.getText();
        dialogContainer.getChildren().addAll(new UserDialogBox(MainWindowModel.USER_NAME, text));
        Ui.playSound(MainWindowModel.NOTIFICATION_SOUND_FILE);
        CommandOutput output = model.getCommandList().executeCommand(text, model.getTaskList());
        dialogContainer.getChildren().addAll(new BotDialogBox(MainWindowModel.BOT_NAME, output.output));
        Ui.playSound(output.sfxFile);
        userInput.clear();
    }
}