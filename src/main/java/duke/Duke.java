package duke;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EditTaskMarkCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
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
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;
    private boolean isRunning;
    private Storage storage;

    //command keywords
    private static final String BYE_COMMAND = "bye";
    private static final String FIND_COMMAND = "find";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";

    //file paths
    private static final String STORAGE_FILE_NAME = "data.txt";
    private static final String DIR_FILE_NAME = "./data/";

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
            ui.printError(exception.getMessage());
        }

        // init parser
        HashMap<String, Command> commands = new HashMap<String, Command>();
        commands.put(BYE_COMMAND, new ByeCommand(BYE_COMMAND ));
        commands.put(FIND_COMMAND, new FindCommand(FIND_COMMAND));
        commands.put(LIST_COMMAND, new ListCommand(LIST_COMMAND));
        commands.put(MARK_COMMAND, new EditTaskMarkCommand(MARK_COMMAND, true));
        commands.put(UNMARK_COMMAND, new EditTaskMarkCommand(UNMARK_COMMAND, false));
        commands.put(TODO_COMMAND, new TodoCommand(TODO_COMMAND));
        commands.put(DEADLINE_COMMAND, new DeadlineCommand(DEADLINE_COMMAND));
        commands.put(EVENT_COMMAND, new EventCommand(EVENT_COMMAND));
        commands.put(DELETE_COMMAND, new DeleteCommand(DELETE_COMMAND));

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
     * Main function of Duke.
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
                if (command.getKey().equals(BYE_COMMAND)) {
                    this.isRunning = false;
                }

                ui.printResponse(response);
            } catch (DukeException error) {
                ui.printError(error.getMessage());
            }
        }

        sc.close();
    }
}
