package duke.parser;

import java.time.LocalDate;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
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
     * Parses the input string and returns the matching Command object that executes the command.
     *
     * @param input String representing user input.
     * @return Command object.
     * @throws DukeException if the input is not a valid command.
     */
    public static Command parseCommand(String input) throws DukeException {
        if (input.isBlank()) {
            throw new DukeException(ErrorMessage.MESSAGE_INVALID_INPUT);
        }
        String command = input.split(" ")[0];
        String[] args = input.split(" ", 2);
        switch (command) {
        case "list":
            return new ListCommand();
            // Fallthrough
        case "delete":
            if (args.length == 1) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_INDEX);
            }
            return new DeleteCommand(Integer.parseInt(args[1]));
            // Fallthrough
        case "mark":
            if (args.length == 1) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_INDEX);
            }
            return new MarkCommand(Integer.parseInt(args[1]));
            // Fallthrough
        case "unmark":
            if (args.length == 1) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_INDEX);
            }
            return new UnmarkCommand(Integer.parseInt(args[1]));
            // Fallthrough
        case "deadline":
            if (args.length == 1) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DESC);
            }
            assert args[1].contains("/by") : "Deadline should contain '/by'";
            String[] deadline = args[1].split("/by", 2);
            if (deadline.length == 1) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DATE);
            }
            return new AddCommand(new Deadline(deadline[0], parseDate(deadline[1])));
            // Fallthrough
        case "event":
            if (args.length == 1) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DESC);
            }
            assert args[1].contains("/at") : "Event should contain '/at'";
            String[] event = args[1].split("/at", 2);
            if (event.length == 1) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DATE);
            }
            return new AddCommand(new Event(event[0], parseDate(event[1])));
            // Fallthrough
        case "todo":
            if (args.length == 1) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DESC);
            }
            return new AddCommand(new Todo(args[1]));
            // Fallthrough
        case "find":
            if (args.length == 1) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_KEYWORD);
            }
            return new FindCommand(args[1]);
        case "bye":
            return new ExitCommand();
            // Fallthrough
        default:
            throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses the date string and returns a LocalDate object.
     *
     * @param date Date string to be parsed.
     * @return LocalDate object.
     * @throws DukeException if the date is invalid.
     */
    public static LocalDate parseDate(String date) throws DukeException {
        String[] splitDate = date.trim().split("-");
        if (splitDate.length != 3) {
            throw new DukeException(ErrorMessage.MESSAGE_INVALID_DATE_FORMAT);
        }
        return LocalDate.of(Integer.parseInt(splitDate[0]),
                Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[2]));
    }
}
