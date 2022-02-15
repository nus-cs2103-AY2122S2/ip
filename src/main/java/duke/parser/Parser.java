package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.UpdateCommand;
import duke.exception.DukeException;
import duke.exception.ErrorString;

/**
 * Parser parses and handles user command.
 *
 * @author yl-ang
 */
public class Parser {

    /**
     * Parses user input and return the command object based on user input.
     *
     * @param fullCommand FullCommand specified by the user.
     * @return Command object based on the user specified command.
     * @throws DukeException if command supplied is not a valid index.
     */
    public static Command parse(String fullCommand) throws DukeException {

        Command currCommand;
        String[] commandString = fullCommand.split(" ");
        int i;

        switch(commandString[0]) {
        case "list":
            currCommand = new ListCommand();
            break;
        case "mark":
            try {
                i = Integer.parseInt(fullCommand.substring("mark".length() + 1));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(ErrorString.ERROR_EMPTY_MARK_INT.toString());
            } catch (NumberFormatException e) {
                throw new DukeException(ErrorString.ERROR_NOT_INT_MARK.toString());
            }
            currCommand = new MarkCommand(i);
            break;
        case "unmark":
            try {
                i = Integer.parseInt(fullCommand.substring("unmark".length() + 1));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(ErrorString.ERROR_EMPTY_UNMARKED_INT.toString());
            } catch (NumberFormatException e) {
                throw new DukeException(ErrorString.ERROR_NOT_INT_UNMARKED.toString());
            }

            currCommand = new UnmarkCommand(i);
            break;
        case "delete":
            try {
                i = Integer.parseInt(fullCommand.substring("delete".length() + 1));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(ErrorString.ERROR_EMPTY_DELETE.toString());
            } catch (NumberFormatException e) {
                throw new DukeException(ErrorString.ERROR_NOT_NUMBER_STRING.toString());
            }

            currCommand = new DeleteCommand(i);
            break;
        case "todo":
            currCommand = new AddCommand(fullCommand, "todo");
            break;
        case "deadline":
            currCommand = new AddCommand(fullCommand, "deadline");
            break;
        case "event":
            currCommand = new AddCommand(fullCommand, "event");
            break;
        case "find":
            currCommand = new FindCommand(fullCommand);
            break;
        case "update":
            currCommand = new UpdateCommand(fullCommand);
            break;
        case "bye":
            currCommand = new ExitCommand();
            break;
        default:
            throw new DukeException(ErrorString.ERROR_INVALID_COMMAND.toString());
        }
        return currCommand;
    }
}
