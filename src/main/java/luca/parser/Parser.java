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
import luca.command.SortCommand;
import luca.command.UnmarkCommand;
import luca.common.DukeException;
import luca.parser.exceptions.InvalidArgumentException;
import luca.parser.exceptions.InvalidDateTimeFormatException;
import luca.parser.exceptions.UnkownCommandException;
import luca.task.TaskType;

/**
 *  Parses the user input.
 */
public class Parser {

    /** Error message when the event date time format is invalid. */
    private static final String INVALID_EVENT_ERROR_MESSAGE = "Please enter the date and "
            + "start and end times in the following format:\n"
            + " yyyy-mm-dd HHMM HHMM in the 24 hour format";

    /** Error message when the Deadline date time format is invalid. */
    private static final String INVALID_DEADLINE_ERROR_MESSAGE = "Please enter the date/time in "
            + "the following format:\n yyyy-mm-dd HHMM in the 24 hour format";

    /** Default time given for deadline if not time was inputted. */
    private static final String DEFAULT_DEADLINE_TIME = "T06:00:00";

    /**
     * Parses input tokens and creates Mark command.
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
     * Parses input tokens and creates Unmark command.
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
     * Parses input tokens and creates a CreateToDoCommand.
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
     * Parses input tokens and creates Delete Command.
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
     * Parses input tokens and creates CreateDeadlineCommand.
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
                        dateTime = LocalDateTime.parse(tokens[i] + DEFAULT_DEADLINE_TIME);
                    } else {
                        dateTime = parseDateTime(tokens[i], tokens[i + 1]);
                    }
                    i++;
                } catch (NumberFormatException | DateTimeParseException exception) {
                    throw new InvalidDateTimeFormatException(INVALID_DEADLINE_ERROR_MESSAGE);
                }
            }
        }
        if (!foundKeyword) {
            throw new InvalidArgumentException(":-( OOPS!!! Due date/time of deadline cannot be empty.");
        }

        assert description.length() > 0 : "Description is missing.";
        assert dateTime != null : "Date and Time is missing.";

        return new CreateDeadlineCommand(description, dateTime);
    }

    /**
     * Parses input tokens and creates a CreateEventCommand.
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
                        throw new InvalidDateTimeFormatException(INVALID_EVENT_ERROR_MESSAGE);
                    }
                    start = parseDateTime(tokens[i], tokens[i + 1]);
                    end = parseDateTime(tokens[i], tokens[i + 2]);

                } catch (NumberFormatException | DateTimeParseException exception) {
                    throw new InvalidDateTimeFormatException(INVALID_EVENT_ERROR_MESSAGE);
                }
                i += 2;
            }

        }

        if (start == null || end == null) {
            throw new InvalidDateTimeFormatException(INVALID_EVENT_ERROR_MESSAGE);
        }

        if (!foundKeyword) {
            throw new InvalidArgumentException(":-( OOPS!!! Start-End date/time of event "
                    + "cannot be empty.");
        }

        assert description.length() > 0 : "Description is missing.";
        assert start != null : "Start date and time is missing.";
        assert end != null : "End date and time is missing.";

        return new CreateEventCommand(description, start, end);
    }

    /**
     * Parses input tokens and creates LocalDateTime object.
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
        assert time > 0 : "Value given for time is negative.";

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
     * Parses input tokens and creates a FindCommand.
     *
     * @param tokens input tokens to be parsed.
     * @return FindCommand.
     * @throws InvalidArgumentException if there are incorrect number of arguments.
     */
    private static Command parseFindCommand(String[] tokens) throws InvalidArgumentException {
        if (tokens.length != 2) {
            throw new InvalidArgumentException(":-( OOPS!!! Please enter only one keyword "
                    + "following the find command");
        }

        return new FindCommand(tokens[1]);
    }

    /**
     * Parses input token and create
     *
     * @param tokens input tokens to be parsed.
     * @return SortCommand.
     * @throws InvalidArgumentException if number of arguments are incorrect,
     *                                  invalid arguments or unkown keywords.
     */
    private static Command parseSortCommand(String[] tokens) throws InvalidArgumentException {
        if (tokens.length < 2 || tokens.length > 3) {
            throw new InvalidArgumentException(":-( OOPS!!! Please enter the task type and order"
                    + "following the sort command\n"
                    + "eg: sort deadline desc");
        }

        TaskType taskType;
        switch (tokens[1].toLowerCase()) {
        case "deadline":
            taskType = TaskType.DEADLINE;
            break;
        case "todo":
            throw new InvalidArgumentException("Sorry! Todo tasks cannot be sorted date/time.");
        case "event":
            taskType = TaskType.EVENT;
            break;
        default:
            throw new InvalidArgumentException("Sorry! Unknown task type: " + tokens[1]);
        }

        Boolean isAscending = true;
        if (tokens.length == 2) {
            // By default creates a new SortCommand with ascending order, if order not specified.
            return new SortCommand(taskType, isAscending);
        }

        String order = tokens[2].toLowerCase();
        if (order.equals("desc") || order.equals("descending")) {
            isAscending = false;
        } else if (order.equals("asc") || order.equals("ascending")) {
            isAscending = true;
        } else {
            throw new InvalidArgumentException("Sorry! Unknown order: " + tokens[2]);
        }

        return new SortCommand(taskType, isAscending);
    }

    /**
     * Parse through the command and creates a command accordingly.
     *
     * @param input user input string.
     * @return Command to be run.
     * @throws DukeException thrown when incorrect format of invalid arguments are used.
     */
    public static Command parse(String input) throws DukeException {

        assert input.length() > 0 : "Empty input string.";

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
        case "sort":
            command = parseSortCommand(tokens);
            break;
        default:
            throw new UnkownCommandException();
        }
        return command;
    }
}
