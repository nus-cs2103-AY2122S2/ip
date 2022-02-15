package duke.manager;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.command.DeleteTaskCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UpdateDeadlineCommand;
import duke.command.UpdateEventCommand;
import duke.command.UpdateToDoCommand;
import duke.exception.DukeException;


/**
 * Represents a parser object to parse the input that is given by the user.
 */
public class Parser {

    /**
     * Returns the appropriate command depending on the fullCommand input that was given by the user.
     *
     * @param fullCommand A string, to be parsed, that was taken in from the user of the program.
     * @return The appropriate command depending on the parsed input.
     * @throws DukeException If an invalid task number was given,
     *                       date/time was not given,
     *                       empty task description or invalid command.
     */
    public Command parse(String fullCommand) throws DukeException {
        fullCommand = fullCommand.trim();
        String keyword = fullCommand.split(" ")[0];
        assert fullCommand.split(" ").length >= 1 : "Should have at least 1 word";
        switch (keyword) {
        case "list":
            return parseListCommand();
        case "mark":
            return parseMarkCommand(fullCommand, true);
        case "unmark":
            return parseMarkCommand(fullCommand, false);
        case "todo":
            return parseAddToDoCommand(fullCommand);
        case "deadline":
            return parseAddDeadlineCommand(fullCommand);
        case "event":
            return parseAddEventCommand(fullCommand);
        case "delete":
            return parseDeleteTaskCommand(fullCommand);
        case "bye":
            return parseExitCommand();
        case "find":
            return parseFindCommand(fullCommand);
        case "update":
            return parseUpdateCommand(fullCommand);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns a mark command.
     *
     * @param fullCommand The full command that was given by the user.
     * @param isMark Whether to mark or unmark the task.
     * @return The command to be executed.
     * @throws DukeException If there is an error parsing the input.
     */
    private MarkCommand parseMarkCommand(String fullCommand, boolean isMark) throws DukeException {
        if (fullCommand.split(" ").length == 1) {
            throw new DukeException("OOPS!!! Please choose a task number");
        }
        int taskNo = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
        return new MarkCommand(isMark, taskNo);
    }

    /**
     * Returns a AddToDoCommand.
     *
     * @param fullCommand The full command that was given by the user.
     * @return The command to be executed.
     * @throws DukeException If there is an error parsing the input.
     */
    private AddToDoCommand parseAddToDoCommand(String fullCommand) throws DukeException {
        String task = fullCommand.replaceFirst("todo", "");
        if (task.length() == 0 || task.trim().length() == 0) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty");
        }
        return new AddToDoCommand(task);
    }

    /**
     * Returns a AddDeadlineCommand.
     *
     * @param fullCommand The full command that was given by the user.
     * @return The command to be executed.
     * @throws DukeException If there is an error parsing the input.
     */
    private AddDeadlineCommand parseAddDeadlineCommand(String fullCommand) throws DukeException {
        String[] text = fullCommand.replaceFirst("deadline", "").split(" /by ");
        String task = text[0];
        if (task.trim().length() == 0) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        if (text.length == 1) {
            throw new DukeException("OOPS!!! The date/time of a deadline cannot be empty.");
        }
        String by = text[1];
        return new AddDeadlineCommand(task, by);
    }

    /**
     * Returns a AddEventCommand.
     *
     * @param fullCommand The full command that was given by the user.
     * @return The command to be executed.
     * @throws DukeException If there is an error parsing the input.
     */
    private AddEventCommand parseAddEventCommand(String fullCommand) throws DukeException {
        String[] text = fullCommand.replaceFirst("event", "").split(" /at ");
        String task = text[0];
        if (task.trim().length() == 0) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
        String at = text[1];
        return new AddEventCommand(task, at);
    }

    /**
     * Returns a DeleteTaskCommand.
     *
     * @param fullCommand The full command that was given by the user.
     * @return The command to be executed.
     * @throws DukeException If there is an error parsing the input.
     */
    private DeleteTaskCommand parseDeleteTaskCommand(String fullCommand) throws DukeException {
        if (fullCommand.split(" ").length == 1) {
            throw new DukeException("OOPS!!! Please choose a task number.");
        }
        int taskNo = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
        return new DeleteTaskCommand(taskNo);
    }

    /**
     * Returns a FindCommand.
     *
     * @param fullCommand The full command that was given by the user.
     * @return The command to be executed.
     * @throws DukeException If there is an error parsing the input.
     */
    private FindCommand parseFindCommand(String fullCommand) throws DukeException {
        if (fullCommand.split(" ").length == 1) {
            throw new DukeException("OOPS!!! Please enter something for me to find!");
        }
        String toFind = fullCommand.split(" ")[1];
        return new FindCommand(toFind);
    }

    /**
     * Returns a ListCommand.
     *
     * @return A ListCommand.
     */
    private ListCommand parseListCommand() {
        return new ListCommand();
    }

    /**
     * Returns an ExitCommand.
     *
     * @return An ExitCommand.
     */
    private ExitCommand parseExitCommand() {
        return new ExitCommand();
    }

    /**
     * Returns the appropriate Command to update the task.
     *
     * @param fullCommand The full command that was given by the user.
     * @return An update Command that is specific to the type of task to update.
     * @throws DukeException If a task type was not specific or the format was not adhered to.
     */
    private Command parseUpdateCommand(String fullCommand) throws DukeException {
        String[] arguments = fullCommand.split(" ");
        if (arguments.length <= 3) {
            throw new DukeException("Please follow the command format!");
        }
        switch (arguments[1]) {
        case "event":
            return new UpdateEventCommand(fullCommand);
        case "deadline":
            return new UpdateDeadlineCommand(fullCommand);
        case "todo":
            return new UpdateToDoCommand(fullCommand);
        default:
            throw new DukeException("OOPS!!! Please enter a task type!");
        }
    }


}
