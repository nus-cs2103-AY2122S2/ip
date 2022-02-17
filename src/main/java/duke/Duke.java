package duke;

import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.function.Function;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EditTaskMarkCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;
import duke.util.Parser;
import duke.util.Storage;

/**
 * Duke chatbot behavior and data.
 */
public class Duke {
    public static final String GREETING = "HELLO CHILD. WHAT DO YOU WISH TO CHAT ABOUT?? /help TO TELL ME!";

    //file paths
    private static final String STORAGE_FILE_NAME = "data.txt";
    private static final String DIR_FILE_NAME = "./data/";

    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;
    private boolean isRunning;
    private Storage storage;

    /**
     * Duke constructor. Initializes values.
     */
    public Duke() {
        taskList = new TaskList();
        ui = new Ui();
        isRunning = true;

        // init storage
        Function<String, Task> taskFactory = (String info) -> {
            Task newTask = null;
            char type = info.charAt(0);
            if (type == Todo.TODO_SYMBOL) {
                newTask = new Todo();
            } else if (type == Event.EVENT_SYMBOL) {
                newTask = new Event();
            } else if (type == Deadline.DEADLINE_SYMBOL) {
                newTask = new Deadline();
            } else {
                newTask = null;
            }

            return newTask;
        };

        try {
            storage = new Storage(STORAGE_FILE_NAME, DIR_FILE_NAME);
            storage.loadFromSave(taskList.getTaskList(), taskFactory);
        } catch (DukeException exception) {
            storage = new Storage();
            ui.printError(exception.getMessage());
        }

        // init parser
        HashMap<String, Command> commands = new HashMap<String, Command>();
        commands.put(ByeCommand.BYE_COMMAND, new ByeCommand());
        commands.put(FindCommand.FIND_COMMAND, new FindCommand());
        commands.put(ListCommand.LIST_COMMAND, new ListCommand());
        commands.put(EditTaskMarkCommand.MARK_COMMAND, new EditTaskMarkCommand(true));
        commands.put(EditTaskMarkCommand.UNMARK_COMMAND, new EditTaskMarkCommand(false));
        commands.put(TodoCommand.TODO_COMMAND, new TodoCommand());
        commands.put(DeadlineCommand.DEADLINE_COMMAND, new DeadlineCommand());
        commands.put(EventCommand.EVENT_COMMAND, new EventCommand());
        commands.put(DeleteCommand.DELETE_COMMAND, new DeleteCommand());
        commands.put(HelpCommand.HELP_COMMAND, new HelpCommand());

        parser = new Parser(commands);
    }

    /**
     * Gets Duke response from an input.
     *
     * @param input User input.
     * @return Duke response.
     * @throws DukeException If there are issues with command execution.
     */
    public String getResponse(String input) throws DukeException {
        Command command = parser.parse(input);
        return command.execute(input, taskList, storage);
    }

    /**
     * Starts and run Duke.
     *
     * @param args NIL
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runDuke();
    }

    /**
     * Runs duke logic and behavior in text form.
     */
    public void runDuke() {
        ui.startGreeting();
        Scanner sc = new Scanner(System.in);

        while (this.isRunning) {
            String userResponse = sc.nextLine();

            try {
                Command command = parser.parse(userResponse);
                String response = command.execute(userResponse, taskList, storage);
                if (command.getKey().equals(ByeCommand.BYE_COMMAND)) {
                    this.isRunning = false;
                }

                ui.printResponse(response);
            } catch (DukeException error) {
                ui.printError(error.getMessage());
            }
        }

        sc.close();
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    /**
     * Checks whether to end Duke or not.
     *
     * @param input User input.
     * @return Whether the command is to end Duke.
     */
    public boolean isEndDukeCommand(String input) {
        StringTokenizer st = new StringTokenizer(input, " ");
        String command = st.nextToken();

        return command.equals(ByeCommand.BYE_COMMAND);
    }
}
