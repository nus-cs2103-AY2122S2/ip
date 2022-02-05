package luca.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import luca.command.Command;
import luca.command.CreateDeadlineCommand;
import luca.command.CreateEventCommand;
import luca.command.CreateToDoCommand;
import luca.command.DeleteCommand;
import luca.command.ExitCommand;
import luca.command.FindCommand;
import luca.command.ListCommand;
import luca.command.MarkCommand;
import luca.command.UnmarkCommand;
import luca.common.DukeException;

/**
 *  Parses the user input.
 */
public class Parser {

    /**
     * Parse input tokens and creates Mark command.
     *
     * @param tokens input tokens to be parsed.
     * @return Mark Command.
     * @throws DukeException if there are invalid arguments.
     */
    private static Command parseMarkCommand(String[] tokens) throws DukeException {
        if (tokens.length < 2) {
            throw new InvalidArgumentException(":-( OOPS!!! Please indicate "
                    + "the task to be marked.");
        }
        try {
            int point = Integer.parseInt(tokens[1]);
            return new MarkCommand(point);
        } catch (NumberFormatException exception) {
            throw new InvalidArgumentException(":-( OOPS!!! Please input an "
                    + "integer with the mark command.");
        }
    }

    /**
     * Parse input tokens and creates Unmark command.
     *
     * @param tokens input tokens to be parsed.
     * @return Unmark Command.
     * @throws DukeException if there are invalid arguments.
     */
    private static Command parseUnmarkCommand(String[] tokens) throws DukeException {
        if (tokens.length < 2) {
            throw new InvalidArgumentException(":-( OOPS!!! Please indicate "
                    + "the task to be unmasked.");
        }
        try {
            int point = Integer.parseInt(tokens[1]);
            return new UnmarkCommand(point);
        } catch (NumberFormatException exception) {
            throw new InvalidArgumentException(":-( OOPS!!! Please input an "
                    + "integer with the unmark command.");
        }
    }

    /**
     * Parse input tokens and creates a CreateToDoCommand.
     *
     * @param tokens input tokens to be parsed.
     * @return CreateToDoCommand.
     * @throws DukeException if there are invalid arguments.
     */
    private static Command parseTodoCommand(String[] tokens) throws DukeException {
        if (tokens.length < 2) {
            throw new InvalidArgumentException(":-( OOPS!!! The description of "
                    + "ToDo cannot be empty.");
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            stringBuilder.append(tokens[i] + " ");
        }
        String description = stringBuilder.toString().trim();
        return new CreateToDoCommand(description);
    }

    /**
     * Parse input tokens and creates Delete Command.
     *
     * @param tokens input tokens to be parsed.
     * @return DeleteCommand.
     * @throws if there are invalid arguments.
     */
    private static Command parseDeleteCommand(String[] tokens) throws DukeException {
        if (tokens.length < 2) {
            throw new InvalidArgumentException(":-( OOPS!!! Please indicate "
                    + "the task to be deleted.");
        }
        try {
            int point = Integer.parseInt(tokens[1]);
            return new DeleteCommand(point);
        } catch (NumberFormatException exception) {
            throw new InvalidArgumentException(":-( OOPS!!! Please input an "
                    + "integer with the delete command.");
        }
    }

    /**
     * Parse input tokens and creates CreateDeadlineCommand.
     *
     * @param tokens input tokens to be parsed.
     * @return CreateDeadlineCommand.
     * @throws DukeException if there are invalid arguments.
     */
    private static Command parseDeadlineCommand(String[] tokens) throws DukeException {
        if (tokens.length < 2) {
            throw new InvalidArgumentException(":-( OOPS!!! The description of "
                    + "deadline cannot be empty.");
        }
        StringBuilder stringBuilder = new StringBuilder();
        Boolean foundKeyword = false;
        LocalDateTime dateTime = null;
        String description = "";
        for (int i = 1; i < tokens.length; i++) {
            if (!foundKeyword) {
                if (tokens[i].equals("/by")) {
                    description = stringBuilder.toString().trim();
                    foundKeyword = true;
                } else {
                    stringBuilder.append(tokens[i]);
                    if (i != tokens.length - 1) {
                        stringBuilder.append(" ");
                    }
                }
            } else {
                try {
                    if (i == tokens.length - 1) {
                        dateTime = LocalDateTime.parse(tokens[i] + "T06:00:00");
                    } else {

                        dateTime = parseDateTime(tokens[i], tokens[i + 1]);
                    }
                    i++;
                } catch (NumberFormatException | DateTimeParseException exception) {
                    throw new InvalidDateTimeFormatException("Please enter the date/time in "
                                + "the following format:\n yyyy-mm-dd HHMM in the 24 hour format");
                }
            }
        }
        if (!foundKeyword) {
            throw new InvalidArgumentException(":-( OOPS!!! Due date/time of deadline cannot be empty.");
        }
        return new CreateDeadlineCommand(description, dateTime);
    }

    /**
     * Parse input tokens and creates a CreateEventCommand.
     *
     * @param tokens input tokens to be parsed.
     * @return CreateEventCommand.
     * @throws DukeException if there are invalid arguments.
     */
    private static Command parseEventCommand(String[] tokens) throws DukeException {
        if (tokens.length < 2) {
            throw new InvalidArgumentException(":-( OOPS!!! The description of "
                    + "event cannot be empty.");
        }
        StringBuilder stringBuilder = new StringBuilder();
        Boolean foundKeyword = false;
        LocalDateTime start = null;
        LocalDateTime end = null;
        String description = "";
        for (int i = 1; i < tokens.length; i++) {
            if (!foundKeyword) {
                if (tokens[i].equals("/at")) {
                    description = stringBuilder.toString().trim();
                    foundKeyword = true;
                } else {
                    stringBuilder.append(tokens[i]);
                    if (i != tokens.length - 1) {
                        stringBuilder.append(" ");
                    }
                }
            } else {
                try {
                    if (i > tokens.length - 3) {
                        throw new InvalidDateTimeFormatException("Please enter the date and "
                                + "start and end times in the following format:\n"
                                + " yyyy-mm-dd HHMM HHMM in the 24 hour format");
                    }
                    start = parseDateTime(tokens[i], tokens[i + 1]);
                    end = parseDateTime(tokens[i], tokens[i + 2]);

                } catch (NumberFormatException | DateTimeParseException exception) {
                    throw new InvalidDateTimeFormatException("Please enter the date and "
                            + "start and end times in the following format:\n"
                            + " yyyy-mm-dd HHMM HHMM in the 24 hour format");
                }
                i += 2;
            }

        }
        if (!foundKeyword) {
            throw new InvalidArgumentException(":-( OOPS!!! Start-End date/time of event "
                    + "cannot be empty.");
        }
        return new CreateEventCommand(description, start, end);
    }

    /**
     * Parse input tokens and creates LocalDateTime object.
     *
     * @param stringDate date as a string.
     * @param stringTime time as a string.
     * @return LocalDateTime object
     * @throws NumberFormatException if integer is not given.
     * @throws DateTimeException if the date format is incorrect.
     */
    private static LocalDateTime parseDateTime(String stringDate, String stringTime)
            throws NumberFormatException, DateTimeException {
        int time;

        time = Integer.parseInt(stringTime);

        String hours;
        String minutes;

        if (time / 100 > 9) {
            hours = Integer.toString(time / 100);
        } else {
            hours = "0" + (time / 100);
        }
        if (time % 100 > 9) {
            minutes = Integer.toString(time % 100);
        } else {
            minutes = "0" + (time % 100);
        }
        return LocalDateTime.parse(stringDate + "T" + hours + ":" + minutes + ":00");
    }

    /**
     * Parse input tokens and creates a FindCommand.
     *
     * @param tokens input tokens to be parsed.
     * @return FindCommand.
     * @throws InvalidArgumentException if there are invalid arguments.
     */
    private static Command parseFindCommand(String[] tokens) throws InvalidArgumentException {
        if (tokens.length != 2) {
            throw new InvalidArgumentException(":-( OOPS!!! Please enter only one keyword "
                    + "following the find command");
        }

        return new FindCommand(tokens[1]);
    }

    /**
     * Parse through the command and creates a command accordingly.
     *
     * @param input user input string.
     * @return Command to be run.
     * @throws DukeException thrown when incorrect format of invalid arguments are used.
     */
    public static Command parse(String input) throws DukeException {
        String[] tokens = input.split("\\s+");
        Command command;
        switch (tokens[0]) {
        case "list":
            command = new ListCommand();
            break;
        case "mark":
            command = parseMarkCommand(tokens);
            break;
        case "unmark":
            command = parseUnmarkCommand(tokens);
            break;
        case "todo":
            command = parseTodoCommand(tokens);
            break;
        case "deadline":
            command = parseDeadlineCommand(tokens);
            break;
        case "event":
            command = parseEventCommand(tokens);
            break;
        case "delete":
            command = parseDeleteCommand(tokens);
            break;
        case "bye":
            command = new ExitCommand();
            break;
        case "find":
            command = parseFindCommand(tokens);
            break;
        default:
            throw new UnkownCommandException();
        }
        return command;
    }
}
