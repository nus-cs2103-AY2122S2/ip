package duke.parser;

import java.time.DateTimeException;
import java.time.LocalDate;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Represents a parser that deals with parsing user input and returning the correct command.
 */
public class Parser {
    /**
     * Parses the given date string and returns a LocalDate object.
     *
     * @param date Date string to be parsed.
     * @return LocalDate object.
     * @throws DukeException If the date is invalid.
     */
    public static LocalDate parseDate(String date) throws DukeException {
        String[] splitDate = date.trim().split("-");
        if (splitDate.length != 3) {
            throw new DukeException(ErrorMessage.MESSAGE_INVALID_DATE_FORMAT);
        }
        LocalDate formattedDate;
        try {
            formattedDate = LocalDate.of(Integer.parseInt(splitDate[0]),
                    Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[2]));
        } catch (DateTimeException e) {
            throw new DukeException(ErrorMessage.MESSAGE_INVALID_DATE_VALUE);
        }
        return formattedDate;
    }

    private static void checkIfInputIsValid(String input) throws DukeException {
        if (input.isBlank()) {
            throw new DukeException(ErrorMessage.MESSAGE_INVALID_INPUT);
        }
    }

    private static void checkIfIndexExists(int length) throws DukeException {
        if (length == 1) {
            throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_INDEX);
        }
    }

    private static void checkIfKeywordExists(int length) throws DukeException {
        if (length == 1) {
            throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_KEYWORD);
        }
    }

    private static void checkIfDateExists(int length) throws DukeException {
        if (length == 1) {
            throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DATE);
        }
    }

    private static FindCommand createNewFindCommand(String[] args) throws DukeException {
        checkIfKeywordExists(args.length);
        return new FindCommand(args[1]);
    }

    private static DeleteCommand createNewDeleteCommand(String[] args) throws DukeException {
        checkIfIndexExists(args.length);
        return new DeleteCommand(Integer.parseInt(args[1]));
    }

    private static MarkCommand createNewMarkCommand(String[] args) throws DukeException {
        checkIfIndexExists(args.length);
        return new MarkCommand(Integer.parseInt(args[1]));
    }

    private static UnmarkCommand createNewUnmarkCommand(String[] args) throws DukeException {
        checkIfIndexExists(args.length);
        return new UnmarkCommand(Integer.parseInt(args[1]));
    }

    private static HelpCommand createNewHelpCommand(String[] args) throws DukeException {
        if (args.length == 1) {
            return new HelpCommand("");
        }
        HelpCommand.checkIfCommandExists(args[1]);
        return new HelpCommand(args[1]);
    }

    private static AddCommand createNewAddCommand(String[] args, String taskType) throws DukeException {
        if (args.length == 1) {
            throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DESC);
        }
        switch(taskType) {
        case "deadline":
            assert args[1].contains("/by") : "Deadline should contain '/by'";
            String[] deadline = args[1].split("/by");
            if (deadline.length == 0 || deadline[0].isBlank()) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DESC);
            }
            checkIfDateExists(deadline.length);
            return new AddCommand(new Deadline(deadline[0].trim(), parseDate(deadline[1])));
        case "event":
            assert args[1].contains("/at") : "Event should contain '/at'";
            String[] event = args[1].split("/at");
            if (event.length == 0 || event[0].isBlank()) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DESC);
            }
            checkIfDateExists(event.length);
            return new AddCommand(new Event(event[0].trim(), parseDate(event[1])));
        default:
            return new AddCommand(new Todo(args[1]));
        }
    }

    /**
     * Parses the input string and returns the matching Command object that executes the command.
     *
     * @param input String representing user input.
     * @return Command object.
     * @throws DukeException If the input is not a valid command.
     */
    public static Command parseCommand(String input) throws DukeException {
        checkIfInputIsValid(input);
        String[] args = input.split(" ", 2);
        switch (args[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "help":
            return createNewHelpCommand(args);
        case "find":
            return createNewFindCommand(args);
        case "delete":
            return createNewDeleteCommand(args);
        case "mark":
            return createNewMarkCommand(args);
        case "unmark":
            return createNewUnmarkCommand(args);
        case "deadline":
            return createNewAddCommand(args, "deadline");
        case "event":
            return createNewAddCommand(args, "event");
        case "todo":
            return createNewAddCommand(args, "todo");
        default:
            throw new DukeException(ErrorMessage.MESSAGE_INVALID_INPUT);
        }
    }
}
