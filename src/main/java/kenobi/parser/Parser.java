package kenobi.parser;

import kenobi.command.AddCommand;
import kenobi.command.Command;
import kenobi.command.DeleteCommand;
import kenobi.command.ExitCommand;
import kenobi.command.FindCommand;
import kenobi.command.ListCommand;
import kenobi.command.MarkCommand;
import kenobi.command.UnmarkCommand;
import kenobi.task.Deadline;
import kenobi.task.Event;
import kenobi.task.Task;
import kenobi.task.TaskException;
import kenobi.task.ToDo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

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
    public static Command parseCommand(String str) throws ParseException {
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
            throw new ParseException("Your command is invalid");
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
        String[] dateComponents = str.split("\\s", 2);

        if (dateComponents[0].equals("next")) {
            return nextDayOfTheWeek(dateComponents[1]);
        }

        try {
            return LocalDate.parse(str);
        } catch (DateTimeParseException e) {
            throw new ParseException("I don't understand that date");
        }
    }

    private static LocalDate nextDayOfTheWeek(String str) {
        LocalDate currDate = LocalDate.now();
        TemporalAdjuster adjuster = TemporalAdjusters.next(DayOfWeek.valueOf(str.toUpperCase()));

        return currDate.with(adjuster);
    }

    /**
     * Parses a string representing a task into a Task object.
     *
     * @param taskString The string representing a task.
     * @return the parsed Task object.
     * @throws ParseException if the task string could not be parsed.
     */
    public static Task parseTask(String taskString) throws ParseException, TaskException {
        String[] taskComponents = taskString.split(",.,");

        Task t;
        switch (taskComponents[0]) {
        case "T":
            t = new ToDo(taskComponents[2]);
            break;

        case "D":
            t = new Deadline(taskComponents[2], LocalDate.parse(taskComponents[3]));
            break;

        case "E":
            t = new Event(taskComponents[2], LocalDate.parse(taskComponents[3]));
            break;

        default:
            throw new ParseException("Invalid Task type in the save file");
        }

        if (taskComponents[1].equals("1")) {
            t.markAsDone();
        }

        return t;
    }
}
