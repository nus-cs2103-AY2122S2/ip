package bobby;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import bobby.command.ByeCommand;
import bobby.command.Command;
import bobby.command.DeadlineCommand;
import bobby.command.DeleteCommand;
import bobby.command.EventCommand;
import bobby.command.FindCommand;
import bobby.command.InvalidCommand;
import bobby.command.ListCommand;
import bobby.command.MarkCommand;
import bobby.command.ToDoCommand;
import bobby.command.UnmarkCommand;
import bobby.exception.BobbyException;
import bobby.exception.DeadlineException;
import bobby.exception.DeleteException;
import bobby.exception.EventException;
import bobby.exception.FindException;
import bobby.exception.MarkException;
import bobby.exception.ToDoException;

public class Parser {
    /** Specific date format that Bobby accepts as input */
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Checks whether the command includes a valid date.
     *
     * @param input The date to be inspected.
     * @return True if the date is in the correct format.
     */
    private static boolean isValidDate(String input) {
        if (input.contains("[a-zA-Z]+") || input.length() != 10) {
            return false;
        }
        try {
            SIMPLE_DATE_FORMAT.setLenient(false);
            SIMPLE_DATE_FORMAT.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Parses the command to allow Bobby to understand input.
     * @param fullCommand The command the user inputs.
     * @return The right Command object for execution.
     */
    public static Command parse(String fullCommand) {
        String[] fullCommandArr = fullCommand.split(" ");
        Command command;
        switch(fullCommandArr[0].toLowerCase()) {
        case "bye": command = new ByeCommand();
            break;
        case "list": command = new ListCommand();
            break;
        case "mark": command = parseMark(fullCommand, fullCommandArr);
            break;
        case "unmark": command = parseUnmark(fullCommand, fullCommandArr);
            break;
        case "todo": command = parseToDo(fullCommand);
            break;
        case "deadline": command = parseDeadline(fullCommand);
            break;
        case "event": command = parseEvent(fullCommand);
            break;
        case "delete": command = parseDelete(fullCommand, fullCommandArr);
            break;
        case "find": command = parseFind(fullCommand);
            break;
        default: command = new InvalidCommand();
        }
        return command;
    }
    private static DeadlineCommand parseDeadline(String fullCommand) throws BobbyException {
        if (fullCommand.substring(8).isBlank()) { // nothing after command
            throw new DeadlineException("blank");
        } else if (!fullCommand.contains("/")) { // no "/"
            throw new DeadlineException("no_slash");
        } else if (fullCommand.substring(fullCommand.indexOf("/") + 1).isBlank()) { // nothing after time
            throw new DeadlineException("no_date");
        } else if (!isValidDate(fullCommand.substring(fullCommand.length() - 10))) { // invalid date
            throw new DeadlineException("invalid_date");
        }
        return new DeadlineCommand(fullCommand.substring(fullCommand.indexOf(" ") + 1, fullCommand.indexOf("/") - 1),
                fullCommand.substring(fullCommand.length() - 10));
    }

    private static DeleteCommand parseDelete(String fullCommand, String[] fullCommandArr) throws BobbyException {
        if (fullCommand.substring(6).isBlank()) { // no argument
            throw new DeleteException("empty");
        }
        return new DeleteCommand(fullCommandArr[1]);
    }

    private static EventCommand parseEvent(String fullCommand) throws BobbyException {
        if (fullCommand.substring(5).isBlank()) { // nothing after command
            throw new EventException("blank");
        } else if (!fullCommand.contains("/")) { // no "/"
            throw new EventException("no_slash");
        } else if (fullCommand.substring(fullCommand.indexOf("/") + 1).isBlank()) { // nothing after time
            throw new EventException("no_date");
        } else if (!isValidDate(fullCommand.substring(fullCommand.length() - 10))) { // invalid date
            throw new EventException("invalid_date");
        }
        return new EventCommand(fullCommand.substring(fullCommand.indexOf(" ") + 1, fullCommand.indexOf("/") - 1),
                fullCommand.substring(fullCommand.length() - 10));
    }

    private static FindCommand parseFind(String fullCommand) throws BobbyException {
        if (fullCommand.substring(4).isBlank()) {
            throw new FindException("empty_command");
        }
        String keyword = fullCommand.substring(4).trim().toLowerCase();
        return new FindCommand(keyword);
    }

    private static MarkCommand parseMark(String fullCommand, String[] fullCommandArr) throws BobbyException {
        if (fullCommand.substring(4).isBlank()) { // no argument
            throw new MarkException("empty");
        }
        return new MarkCommand(fullCommandArr[1]);
    }

    private static ToDoCommand parseToDo(String fullCommand) throws BobbyException {
        if (fullCommand.substring(4).isBlank()) { // nothing after command
            throw new ToDoException("todo");
        }
        return new ToDoCommand(fullCommand.substring(5));
    }

    private static UnmarkCommand parseUnmark(String fullCommand, String[] fullCommandArr) throws BobbyException {
        if (fullCommand.substring(6).isBlank()) { // no argument
            throw new MarkException("empty");
        }
        return new UnmarkCommand(fullCommandArr[1]);
    }

}
