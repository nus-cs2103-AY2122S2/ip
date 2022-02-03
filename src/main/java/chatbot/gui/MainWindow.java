package chatbot.gui;

import chatbot.command.CommandOutput;
import chatbot.command.DeadlineCommand;
import chatbot.command.DeleteCommand;
import chatbot.command.EventCommand;
import chatbot.command.FindCommand;
import chatbot.command.ListCommand;
import chatbot.command.MarkCommand;
import chatbot.command.ResetCommand;
import chatbot.command.TerminateCommand;
import chatbot.command.ToDoCommand;
import chatbot.command.UnmarkCommand;
import chatbot.task.TaskList;
import chatbot.util.Parser;
import chatbot.util.Ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    private static final String BOT_NAME = "Delphine";
    private static final String USER_NAME = "You";
    private static final String SAVE_FILE = "./data/save_file";
    private static final String NOTIFICATION_SOUND_FILE = "/audio/notification.wav";

    private Parser parser;
    private TaskList taskList;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    public MainWindow() {
        this.taskList = TaskList.create(SAVE_FILE);
        this.parser = new Parser();
        this.parser.addCommand(new TerminateCommand());
        this.parser.addCommand(new DeadlineCommand());
        this.parser.addCommand(new DeleteCommand());
        this.parser.addCommand(new EventCommand());
        this.parser.addCommand(new ListCommand());
        this.parser.addCommand(new MarkCommand());
        this.parser.addCommand(new ToDoCommand());
        this.parser.addCommand(new UnmarkCommand());
        this.parser.addCommand(new ResetCommand());
        this.parser.addCommand(new FindCommand());
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
        dialogContainer.getChildren().addAll(new DialogBox(USER_NAME, text));
        Ui.playSound("/audio/notification.wav");
        CommandOutput output = parser.executeCommand(text, taskList);
        dialogContainer.getChildren().addAll(new DialogBox(BOT_NAME, output.output));
        Ui.playSound(output.sfxFile);
        userInput.clear();
    }
}
