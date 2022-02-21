package duke.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import duke.DukeException;
import duke.InvalidArgumentException;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Responsible for parsing user input and identifying correctly formatted commands.
 */
public class Parser {

    /**
     * Parses and identifies the command provided by the user.
     *
     * @param strCommand The user's command in String format.
     * @return The Command object corresponding to the user's input.
     * @throws DukeException If the user provides an invalid or incorrectly formatted command.
     */
    public static Command parse(String strCommand) throws DukeException {
        String[] commandSegments = strCommand.split(" ", 2);
        String keyword = commandSegments[0];
        Command command;

        assert !keyword.isBlank();

        switch (keyword) {
        case "bye":
            command = new ExitCommand();

            break;
        case "list":
            command = new ListCommand();

            break;
        case "mark":
            command = parseMarkCommand(commandSegments);
            break;
        case "unmark":
            command = parseUnmarkCommand(commandSegments);
            break;
        case "delete":
            command = parseDeleteCommand(commandSegments);
            break;
        case "todo":
            command = parseAddTodoCommand(commandSegments);
            break;
        case "event":
            command = parseAddEventCommand(commandSegments);
            break;
        case "deadline":
            command = parseAddDeadlineCommand(commandSegments);
            break;
        case "find":
            command = parseFindCommand(commandSegments);
            break;
        default:
            throw new InvalidCommandException("Not an understood command");
        }
        return command;
    }

    /**
     * Determines whether the given String array has two arguments.
     *
     * @param arr The String array.
     * @return True if it has two arguments and false otherwise.
     */
    public static boolean hasTwoArguments(String[] arr) {
        return arr.length == 2 && !arr[1].isBlank();
    }

    /**
     * Parses time in String form into a Date object.
     *
     * @param timeAsString The time in String form.
     * @return The time as a Date object.
     * @throws ParseException If the time cannot be parsed.
     */
    public static Date parseTimeFromString(String timeAsString) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("d/M/yy HH:mm");
        return parser.parse(timeAsString);
    }

    /**
     * Parses a MarkCommand from user input if formatted correctly and valid.
     *
     * @param segments The user input separated into command components.
     * @return The corresponding MarkCommand.
     * @throws DukeException If the command is incorrectly formatted.
     */
    public static MarkCommand parseMarkCommand(String[] segments) throws DukeException {
        if (hasTwoArguments(segments)) {
            try {
                int index = Integer.parseInt(segments[1]);
                return new MarkCommand(index);
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException("Not a valid number");
            }
        } else {
            throw new MissingArgumentException("Missing task number");
        }
    }

    /**
     * Parses a UnmarkCommand from user input if formatted correctly and valid.
     *
     * @param segments The user input separated into command components.
     * @return The corresponding UnmarkCommand.
     * @throws DukeException If the command is incorrectly formatted.
     */
    public static UnmarkCommand parseUnmarkCommand(String[] segments) throws DukeException {
        if (hasTwoArguments(segments)) {
            try {
                int index = Integer.parseInt(segments[1]);
                return new UnmarkCommand(index);
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException("The index number provided is not a valid number");
            }
        } else {
            throw new MissingArgumentException("Missing task index number");
        }
    }

    /**
     * Parses a DeleteCommand from user input if formatted correctly and valid.
     *
     * @param segments The user input separated into command components.
     * @return The corresponding DeleteCommand.
     * @throws DukeException If the command is incorrectly formatted.
     */
    public static DeleteCommand parseDeleteCommand(String[] segments) throws DukeException {
        if (hasTwoArguments(segments)) {
            try {
                int index = Integer.parseInt(segments[1]);
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException("The index number provided is not a valid number");
            }
        } else {
            throw new MissingArgumentException("Missing task index number");
        }
    }

    /**
     * Parses an AddCommand for a Todo from user input if formatted correctly and valid.
     *
     * @param segments The user input separated into command components.
     * @return The corresponding AddCommand.
     * @throws MissingArgumentException If no title is provided for the Todo.
     */
    public static AddCommand parseAddTodoCommand(String[] segments) throws MissingArgumentException {
        if (hasTwoArguments(segments)) {
            String title = segments[1];
            return new AddCommand(new Todo(title));
        } else {
            throw new MissingArgumentException("Missing task title");
        }
    }

    /**
     * Parses an AddCommand for an Event from user input if formatted correctly and valid.
     *
     * @param segments The user input separated into command components.
     * @return The corresponding AddCommand.
     * @throws MissingArgumentException If the input is missing information or is formatted incorrectly.
     */
    public static AddCommand parseAddEventCommand(String[] segments) throws DukeException {
        if (hasTwoArguments(segments)) {
            String[] taskArguments = segments[1].split(" /at", 2);
            if (!taskArguments[0].isBlank() && hasTwoArguments(taskArguments)) {
                String title = taskArguments[0];
                try {
                    Date time = parseTimeFromString(taskArguments[1]);
                    return new AddCommand(new Event(title, time));
                } catch (ParseException e) {
                    throw new InvalidArgumentException("The time format is incorrect");
                }
            } else if (!taskArguments[0].isBlank()) {
                throw new MissingArgumentException("Missing name for the event");
            } else {
                throw new MissingArgumentException("Missing time for the event");
            }
        } else {
            throw new MissingArgumentException("Missing name and time for the event");
        }
    }

    /**
     * Parses an AddCommand for an Deadline from user input if formatted correctly and valid.
     *
     * @param segments The user input separated into command components.
     * @return The corresponding AddCommand.
     * @throws MissingArgumentException If the input is missing information or is formatted incorrectly.
     */
    public static AddCommand parseAddDeadlineCommand(String[] segments) throws DukeException {
        if (hasTwoArguments(segments)) {
            String[] taskArguments = segments[1].split(" /by", 2);
            if (!taskArguments[0].isBlank() && hasTwoArguments(taskArguments)) {
                String title = taskArguments[0];
                try {
                    Date time = parseTimeFromString(taskArguments[1]);
                    return new AddCommand(new Deadline(title, time));
                } catch (ParseException e) {
                    throw new InvalidArgumentException("The time format is incorrect");
                }
            } else if (!taskArguments[0].isBlank()) {
                throw new MissingArgumentException("Missing name for the deadline");
            } else {
                throw new MissingArgumentException("Missing time for the deadline");
            }
        } else {
            throw new MissingArgumentException("Missing name and time for the deadline");
        }
    }

    /**
     * Parses a FindCommand from user input if formatted correctly and valid.
     *
     * @param segments The user input separated into command components.
     * @return The corresponding FindCommand.
     * @throws MissingArgumentException If no keyword or keyphrase is provided.
     */
    public static FindCommand parseFindCommand(String[] segments) throws MissingArgumentException {
        if (hasTwoArguments(segments)) {
            return new FindCommand(segments[1]);
        } else {
            throw new MissingArgumentException("Missing keyword or keyphrase");
        }
    }
}
