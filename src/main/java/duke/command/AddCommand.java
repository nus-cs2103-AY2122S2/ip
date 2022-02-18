package duke.command;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Tasks;
import duke.task.Todos;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a command generalised for the addition of tasks. A AddCommand object corresponds to
 * a command represented by the input command and the arguments by the user e.g., todos, Eat a cake.
 */
public class AddCommand extends Commands {
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String FAILURE_MESSAGE = "";

    private static final boolean IS_EXIT = false;
    private final String commandWord;
    private final String arguments; // In the form of user duke.command

    /**
     * The sole constructor for the AddCommand class.
     *
     * @param commandWord The command the user input.
     * @param arguments The arguments the user input.
     */
    public AddCommand(String commandWord, String arguments) {
        this.commandWord = commandWord;
        this.arguments = arguments;
    }

    /**
     * Returns the apt exit instruction after the command is executed.
     * If the program ends after this is executed, true is returned.
     *
     * @return the apt exit instruction.
     */
    @Override
    public boolean isExit() {
        return IS_EXIT;
    }

    /**
     * Create a task and add it into the database.
     *
     * @param tasks An arraylist of tasks reflective of the current state in the database.
     * @param ui A class that controls the user-interface of the user.
     * @param storage A class that is in-charge of writing, appending, and reading the database.
     * @return A command result with a string reflective of the task's success.
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Tasks> createdTaskList = new ArrayList<>();
        try {
            switch (commandWord) {

            case COMMAND_TODO:
                createdTaskList.add(new Todos(arguments));
                break;

            case COMMAND_EVENT:
                createdTaskList.add(new Events(arguments.split(" /at ", 2)[0],
                        arguments.split(" /at ", 2)[1]));
                break;

            case COMMAND_DEADLINE:
                createdTaskList.add(new Deadlines(arguments.split(" /by ", 2)[0],
                        arguments.split(" /by ", 2)[1]));
                break;

            default:
                break;
            }

            if (createdTaskList.size() > 0) {
                return new CommandResult(tasks.addsTask(createdTaskList.get(0), storage));
            }

        } catch (IndexOutOfBoundsException err) {
            return new CommandResult("Addition of tasks unsuccessful, "
                    + "ensure that you are writing in the correct format.");
        } catch (DateTimeParseException err) {
            return new CommandResult(
                    "Addition of tasks unsuccessful, ensure that the date is valid, "
                            + "and goes by the format of (YYYY-MM-DD).");
        }
        return new CommandResult(FAILURE_MESSAGE);
    }
}
