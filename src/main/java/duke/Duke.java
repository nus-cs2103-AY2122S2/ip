package duke;


import duke.command.Command;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.SnoozeCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.TodoCommand;
import duke.command.DeleteCommand;
import duke.command.HelpCommand;

import javafx.application.Application;
import javafx.stage.Stage;

import duke.task.TaskList;



/**
 * Duke class used for running the Duke chat bot
 */
public class Duke extends Application {

    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    private final Parser parser;
    private DialogContainer dialogContainer;

    /**
     * Default constructor for Duke
     */
    public Duke() {
        ui = new Ui();
        dialogContainer = new DialogContainer();
        parser = new Parser();
        storage = new Storage("save.txt");
        try {
            tasks = new TaskList(storage.parseFile());
        } catch (DukeException e) {
            ui.sayMessage(e.getErrorMsg());
        }
    }

    /**
     * Main function
     *
     * @param args arguments to pass in
     */
    public static void main(String[] args) {
        new Duke();
    }

    /**
     * Overloaded start function inherited from Application
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        dialogContainer.init(stage);

        dukeSayMessage("Hello! I am Duke :)");

        dialogContainer.sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        dialogContainer.userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = dialogContainer.userInput.getText();
        String dukeText = getResponse(dialogContainer.userInput.getText());

        dialogContainer.addChatBox(userText, dukeText);
    }

    private void dukeSayMessage(String text) {
        dialogContainer.addDukeChatBox(text);
    }

    private String getResponse(String input) {
        String response = "What?";
        Command command = null;
        try {
            Parser.RESULT parseResult = parser.parseInput(input);
            switch (parseResult) {
            case BYE:
                storage.overwriteFile(tasks.getTasks());
                response = "Goodbye";
                break;
            case LIST:
                response = ui.listTasksAsString(tasks);
                break;
            case MARK:
                command = new MarkCommand(parser,input,tasks,storage);
                break;
            case UNMARK: {
                command = new UnmarkCommand(parser,input,tasks,storage);
                break;
            }
            case DELETE:
                command = new DeleteCommand(parser,input,tasks,storage);
                break;
            case TODO:
                command = new TodoCommand(parser,input,tasks,storage);
                break;
            case DEADLINE:
                command = new DeadlineCommand(parser,input,tasks,storage);
                break;
            case EVENT:
                command = new EventCommand(parser,input,tasks,storage);
                break;
            case FIND: {
                String word = parser.parseFind(input);
                response = ui.listTasksAsString(tasks.findTasks(word));
                break;
            }
            case SNOOZE:
                command = new SnoozeCommand(parser,input,tasks,storage);
                break;
            case HELP:
                command = new HelpCommand(parser,input,tasks,storage);
                break;
            case ERROR:
                throw new DukeException("Sorry :( I don't know what this means.");
            }
            if (command != null) {
                response = command.execute();
            }
            return response;
        } catch (DukeException e) {
            return e.getErrorMsg();
        }
    }
}