package duke.functionality;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.MarkCommand;
import duke.command.PrintCommand;
import duke.command.UnmarkCommand;
import duke.exception.BlankCommandException;
import duke.exception.DukeException;
import duke.exception.IncompleteCommandException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Represents the Parsing capabilities of the Duke project. A <code> Parse </code> object corresponds
 * to the actions taken to parse and format user inputs.
 */
public class Parser {
    private static final int EVENT_OFFSET = 5;
    private static final int TODO_OFFSET = 4;
    private static final int DEADLINE_OFFSET = 8;
    private static final int INPUT_OFFSET = 3;
    private static final int FIRST_INPUT = 0;
    private static final int SECOND_INPUT = 1;
    private static final int THIRD_INPUT = 2;

    /**
     * Returns the formatted date.
     * @param input date specified by user input. Eg, "2020-06-06".
     * @return formatted date.
     */
    public static LocalDate formatDate(String input) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(input, dtf);
        return date;
    }

    /**
     * Returns the formatted time.
     * @param input time specified by user input. Eg, "1800".
     * @return formatted time.
     */
    public static LocalTime formatTime(String input) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
        LocalTime time = LocalTime.parse(input, dtf);
        return time;
    }

    /**
     * Returns the date.
     * @param input formatted date. Eg, "2020-06-06".
     * @return date but as a String.
     */
    public static String dateToString(LocalDate input) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = input.format(dtf);
        return date;
    }

    /**
     * Returns the time.
     * @param input formatted time Eg, "1800".
     * @return time but as a String.
     */
    public static String timeToString(LocalTime input) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
        String time = input.format(dtf);
        return time;
    }

    private static void checkDescriptionLength(String description, String command) throws IncompleteCommandException {
        if (description.length() == 0) {
            throw new IncompleteCommandException(command);
        }
    }

    private static Command handleDeadline(String input, String command) throws DukeException {
        String[] inputSlash = input.split("/");
        String description = inputSlash[FIRST_INPUT].substring(DEADLINE_OFFSET).trim();
        checkDescriptionLength(description, command);

        String durationInput = inputSlash[SECOND_INPUT].substring(INPUT_OFFSET);
        String[] splitDuration = durationInput.split(" ");

        try {
            LocalDate date = formatDate(splitDuration[FIRST_INPUT]);
            LocalTime time = formatTime(splitDuration[SECOND_INPUT]);
            return new AddCommand(new Deadline(description, date, time));

        } catch (DateTimeParseException e) {
            throw new DukeException("The expected input form is deadline xxx /by yyyy-mm-dd hhmm");
        }
    }

    private static Command handleEvent(String input, String command) throws DukeException {
        String[] inputSlash = input.split("/");
        String description = inputSlash[FIRST_INPUT].substring(EVENT_OFFSET).trim();
        checkDescriptionLength(description, command);

        String durationInput = inputSlash[SECOND_INPUT].substring(INPUT_OFFSET);
        String[] splitDuration = durationInput.split(" ");

        try {
            LocalDate date = formatDate(splitDuration[FIRST_INPUT]);
            LocalTime startTime = formatTime(splitDuration[SECOND_INPUT]);
            LocalTime endTime = formatTime(splitDuration[THIRD_INPUT]);
            return new AddCommand(new Event(description, date, startTime, endTime));
        } catch (DateTimeParseException e) {
            throw new DukeException("The expected input form is event xxx /at yyyy-mm-dd hhmm hhmm");
        }
    }

    private static Command handleTodo(String input, String command) throws DukeException {
        String description = input.substring(TODO_OFFSET).trim();
        checkDescriptionLength(description, command);
        return new AddCommand(new Todo(description));
    }

    /**
     * Returns the respective command from user input.
     * @param input user input. Eg, "todo run".
     * @return the command.
     * @throws DukeException when user input for date and time are not of the correct form.
     */
    public static Command parse(String input) throws DukeException {
        String[] inputSplit = input.split(" ");
        String command = inputSplit[FIRST_INPUT];
        if (command.equals("bye")) {
            return new ExitCommand();

        } else if (command.equals("list")) {
            return new PrintCommand();

        } else if (command.equals("todo")) {
            return handleTodo(input, command);

        } else if (command.equals("deadline")) {
            return handleDeadline(input, command);

        } else if (command.equals("event")) {
            return handleEvent(input, command);

        } else if (command.equals("mark")) {
            return new MarkCommand(Integer.parseInt(inputSplit[SECOND_INPUT]));

        } else if (command.equals("unmark")) {
            return new UnmarkCommand(Integer.parseInt(inputSplit[SECOND_INPUT]));

        } else if (command.equals("delete")) {
            return new DeleteCommand(Integer.parseInt(inputSplit[SECOND_INPUT]));

        } else if (command.equals("find")) {
            return new FindCommand(inputSplit[SECOND_INPUT]);

        } else if (command.equals("")) {
            throw new BlankCommandException();

        } else {
            throw new InvalidCommandException();
        }
    }
}
