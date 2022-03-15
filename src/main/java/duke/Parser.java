package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;

/**
 * Class that parses user input and returns the corresponding command.
 */
public class Parser {

    /**
     * Main method that takes in raw user input and returns the corresponding command.
     *
     * @param fullCommand The raw string input by the user.
     * @return The command corresponding to the user's input.
     * @throws DukeException If user's input is invalid (does not correspond to any command or invalid parameters).
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] inputs = fullCommand.split(" ", 2); //return [commandWord, restOfCommand]
        String commandWord = inputs[0];

        switch(commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD: {
            int index = -1;
            try {
                index = Integer.valueOf(inputs[1]) - 1;
                return new MarkCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid parameter(s). Usage: " + Ui.MARK_USAGE);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid parameter(s). Task " + (index + 1) + " does not exist");
            }
        }
        case UnmarkCommand.COMMAND_WORD: {
            int index = -1;
            try {
                index = Integer.valueOf(inputs[1]) - 1;
                return new UnmarkCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid parameter(s). Usage: " + Ui.UNMARK_USAGE);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid parameter(s). Task " + (index + 1) + " does not exist");
            }
        }
        case TodoCommand.COMMAND_WORD:
            try {
                String description = inputs[1];
                return new TodoCommand(description);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid parameter(s). Usage: " + Ui.TODO_USAGE);
            }
        case DeadlineCommand.COMMAND_WORD:
            try {
                String[] args = inputs[1].split(" /by ", 2);
                String description = args[0];
                LocalDate deadline = LocalDate.parse(args[1]);
                return new DeadlineCommand(description, deadline);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please specify your date in the format \"yyyy-mm-dd\"");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid parameter(s). Usage: " + Ui.DEADLINE_USAGE);
            }
        case EventCommand.COMMAND_WORD:
            try {
                String[] args = inputs[1].split(" /at ", 2);
                String description = args[0];
                String at = args[1];
                return new EventCommand(description, at);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid parameter(s). Usage: " + Ui.EVENT_USAGE);
            }
        case DeleteCommand.COMMAND_WORD: {
            int index = -1;
            try {
                index = Integer.parseInt(inputs[1]) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid parameter(s). Usage: " + Ui.DELETE_USAGE);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid parameter(s). Task " + (index + 1) + " does not exist");
            }
        }
        case FindCommand.COMMAND_WORD:
            try {
                String keyword = inputs[1];
                return new FindCommand(keyword);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid parameters. Usage: " + Ui.FIND_USAGE);
            }
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        default:
            throw new DukeException("Not sure what you're saying bro, try \"help\" to display all commands");
        }
    }
}
