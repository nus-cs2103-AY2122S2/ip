package kenobi.parser;

import kenobi.command.AddCommand;
import kenobi.command.Command;
import kenobi.command.DeleteCommand;
import kenobi.command.ExitCommand;
import kenobi.command.FindCommand;
import kenobi.command.InvalidCommand;
import kenobi.command.ListCommand;
import kenobi.command.MarkCommand;
import kenobi.command.UnmarkCommand;
import kenobi.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static java.lang.Integer.parseInt;

/**
 * The parser class encapsulates the parse functionality of Kenobi.
 */
public class Parser {
    /**
     * Parses the given command to a Command object.
     *
     * @param str The received command.
     * @return a Command object encapsulating the parsed command.
     * @throws ParseException if the Parser could not parse the command
     */
    public static Command parse(String str) throws ParseException {
        String[] cmd = str.split("\\s", 2);
        String[] fields;

        switch (cmd[0]) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "find":
            try {
                return new FindCommand(cmd[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new ParseException("the command seems to be incomplete");
            }

        case "mark":
            try {
                return new MarkCommand(parseInt(cmd[1]));
            } catch (IndexOutOfBoundsException e) {
                throw new ParseException("the command seems to be incomplete");
            }

        case "unmark":
            try {
                return new UnmarkCommand(parseInt(cmd[1]));
            } catch (IndexOutOfBoundsException e) {
                throw new ParseException("the command seems to be incomplete");
            }

        case "delete":
            try {
                return new DeleteCommand(parseInt(cmd[1]));
            } catch (IndexOutOfBoundsException e) {
                throw new ParseException("the command seems to be incomplete");
            }

        case "todo":
            try {
                return new AddCommand(cmd[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new ParseException("the command seems to be incomplete");
            }

        case "deadline":
            try {
                fields = cmd[1].split(" /by ");
                return new AddCommand(Task.Type.DEADLINE, fields[0], parseDate(fields[1]));
            } catch (IndexOutOfBoundsException e) {
                throw new ParseException("the command seems to be incomplete");
            }

        case "event":
            try {
                fields = cmd[1].split(" /at ");
                return new AddCommand(Task.Type.EVENT, fields[0], parseDate(fields[1]));
            } catch (IndexOutOfBoundsException e) {
                throw new ParseException("the command seems to be incomplete");
            }

        default:
            return new InvalidCommand();
        }
    }

    /**
     * Parses a string representing a date into a LocalDate object.
     *
     * @param str The date string to be parsed.
     * @return a LocalDate object representing the parsed date string.
     * @throws ParseException if the date string could not be parsed.
     */
    private static LocalDate parseDate(String str) throws ParseException {
        try {
            return LocalDate.parse(str);
        } catch (DateTimeParseException e) {
            throw new ParseException("I don't understand that date");
        }
    }
}
