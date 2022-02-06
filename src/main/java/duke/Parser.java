package duke;

import duke.command.AddCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.ToggleCommand;
import duke.command.ViewScheduleCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * An instance of Parser, used to parse user input to the respective commands.
 */
public class Parser {
    private static boolean isExit = false;

    /**
     * Is exit boolean.
     *
     * @return the boolean
     */
    public static boolean isExit() {
        return isExit;
    }

    /**
     * Sets is exit.
     *
     * @param flag the flag
     */
    public static void setIsExit(boolean flag) {
        isExit = flag;
    }

    /**
     * Parses `String` to `Integer`.
     *
     * @param strToIntValue the `String` that needs to be parsed to an `Integer` value
     * @return the `Integer` value of the `String`.
     * @throws DukeException if the parameter could not be parsed to an `Integer`.
     */
    public static int parseInt(String strToIntValue) throws DukeException {
        try {
            return Integer.parseInt(strToIntValue);
        } catch (Exception e) {
            throw new DukeException("Please provide the item NUMBER to remove.");
        }
    }

    /**
     * Parses a `String` and returns its corresponding `Command`.
     *
     * @param fullCommand the full command containing the keyword and its necessary details
     * @return the corresponding command
     */
    public static Command parseCommand(String fullCommand) throws DukeException {
        // Not sure how to trim (if even necessary) this down since it's all case-checking.
        String[] input = fullCommand.split(" ", 2);

        switch (input[0].toLowerCase()) {
        case "exit":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
        case "unmark":
            return new ToggleCommand(input[0], input[1]);
        case "todo":
        case "event":
        case "deadline":
            return new AddCommand(input[0], input[1]);
        case "remove":
        case "delete":
            return new DeleteCommand(input[1]);
        case "clear":
            return new ClearCommand();
        case "find":
            return new FindCommand(input[1]);
        case "view":
            return new ViewScheduleCommand(input[1]);
        case "help":
            return new HelpCommand();
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Parses a `String` to `Task` when reading from the locally-stored data file.
     *
     * @param entry an entry in the locally-stored data file
     * @return the corresponding tasks initiated with its details and marked status.
     * @throws DukeException if there is an invalid keyword in the local data file
     */
    public static Task parseStringToTask(String entry) throws DukeException {
        String[] input = entry.split(" \\| ");
        Task newTask;
        switch (input[0]) {
        case "T":
            newTask = new ToDo(input[2], input[1]);
            break;
        case "D":
            newTask = new Deadline(input[2], input[3], input[1]);
            break;
        case "E":
            newTask = new Event(input[2], input[3], input[1]);
            break;
        default:
            throw new DukeException("Invalid keyword from source file.");
        }

        return newTask;
    }
}
