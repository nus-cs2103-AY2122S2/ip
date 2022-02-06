package duke.operations;

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
import duke.exceptions.DukeException;
import duke.exceptions.EmptyInputException;
import duke.exceptions.IncompleteInputException;
import duke.exceptions.UnknownInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents a parser for Duke. It deals with making sense of the user command.
 */
public class Parser {
    private static final int EMPTY_INPUT = 0;
    private static final int INDEX_AFTER_DELETE = 1;
    private static final int INDEX_AFTER_MARK = 1;
    private static final int INDEX_AFTER_UNMARK = 1;
    private static final int INPUT_AFTER_AT = 3;
    private static final int INPUT_AFTER_BY = 3;
    private static final int INPUT_AFTER_TODO = 4;
    private static final int INPUT_AFTER_FIND = 4;
    private static final int INPUT_AFTER_EVENT = 5;
    private static final int INPUT_AFTER_DEADLINE = 8;

    /**
     * Converts an input of type String into LocalDate. E.g., 12-12-2022.
     *
     * @param str input date of format "dd-MM-yyyy". E.g., 12-12-2022.
     * @return input of type LocalDate of format "dd-MM-yyyy". E.g., 12-12-2022.
     */
    public static LocalDate convertStringToLocalDate(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(str, formatter);
    }

    /**
     * Converts an input of type String into LocalTime. E.g., 1900.
     *
     * @param str input time of format "HHmm". E.g., 1900.
     * @return input of type LocalTime of format "HHmm". E.g., 1900.
     */
    public static LocalTime convertStringToLocalTime(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return LocalTime.parse(str, formatter);
    }

    /**
     * Converts an input of type LocalDate into String. E.g., 12-12-2022.
     *
     * @param date input date of format "dd-MM-yyyy". E.g., 12-12-2022.
     * @return input of type String of format "dd-MM-yyyy". E.g., 12-12-2022.
     */
    public static String convertLocalDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }

    /**
     * Converts an input of type LocalTime into String. E.g., 1900.
     *
     * @param time input time of format "HHmm". E.g., 1900.
     * @return input of type String of format "HHmm". E.g., 1900.
     */
    public static String convertLocalTimeToString(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return time.format(formatter);
    }

    /**
     * Parses the user input and handles the various commands depending on the input.
     * E.g., if user keys in "todo run", this task will go to the respective if block and handle it.
     *
     * @param input the user input.
     * @return a <code>Command</code> that executes the logic.
     * @throws DukeException handles incomplete and unknown inputs. E.g., "deadline do project /by 12312312".
     */
    public static Command parse(String input) throws DukeException {
        String[] strs = input.split(" ");

        String firstWord = strs[0];

        switch (firstWord) {
        case "":
            throw new EmptyInputException();
        case "bye":
            return new ExitCommand();
        case "list":
            return new PrintCommand();
        case "mark":
            return handleMarkInput(strs[INDEX_AFTER_MARK]);
        case "unmark":
            return handleUnmarkInput(strs[INDEX_AFTER_UNMARK]);
        case "todo":
            return handleTodo(input, firstWord);
        case "deadline":
            return handleDeadline(input, firstWord);
        case "event":
            return handleEvent(input, firstWord);
        case "delete":
            return handleDelete(strs);
        case "find":
            return handleFind(input);
        default:
            throw new UnknownInputException();
        }
    }

    private static Command handleMarkInput(String str) throws DukeException {
        try {
            int listIndex = Integer.parseInt(str); // retrieve the index after mark
            return new MarkCommand(listIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("Come on... don't use sussy language to mark tasks, use numbers!");
        }
    }

    private static Command handleUnmarkInput(String str) throws DukeException {
        try {
            int listIndex = Integer.parseInt(str); // retrieve the index after unmark
            return new UnmarkCommand(listIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("Come on... don't use sussy language to unmark tasks, use numbers!");
        }
    }

    private static Command handleToDoInput(String str) {
        Task toDo = new ToDo(str);
        return new AddCommand(toDo);
    }

    private static Command handleDeadlineInput(String str) throws DukeException {
        // breaks the string into 2 parts, description, date and time.
        String[] inputArgs = str.split(" /");
        if (!inputArgs[1].substring(0, 3).equals("by ")) {
            throw new UnknownInputException();
        } else {
            String description = inputArgs[0];
            String deadlineDateTime = inputArgs[1].substring(INPUT_AFTER_BY);
            String[] dateTimeArgs = deadlineDateTime.split(" ");
            try {
                LocalDate deadlineDate = convertStringToLocalDate(dateTimeArgs[0]);
                LocalTime deadlineTime = convertStringToLocalTime(dateTimeArgs[1]);
                Task deadline = new Deadline(description, deadlineDate, deadlineTime);
                return new AddCommand(deadline);
            } catch (DateTimeParseException e) {
                throw new DukeException("you sussy baka, that's the wrong date format!"
                        + " Enter dd-MM-yyyy HHmm");
            }
        }
    }

    private static Command handleEventInput(String str) throws DukeException {
        // breaks the string into 2 parts, description, date and time.
        String[] inputArgs = str.split(" /");
        if (!inputArgs[1].substring(0, 3).equals("at ")) {
            throw new UnknownInputException();
        } else {
            String description = inputArgs[0];
            String eventDateTime = inputArgs[1].substring(INPUT_AFTER_AT);
            String[] dateTimeArgs = eventDateTime.split(" ");
            try {
                LocalDate eventDate = convertStringToLocalDate(dateTimeArgs[0]);
                String eventTime = dateTimeArgs[1];
                String[] splitEventTimes = eventTime.split("-");
                LocalTime eventStartTime = convertStringToLocalTime(splitEventTimes[0]);
                LocalTime eventEndTime = convertStringToLocalTime(splitEventTimes[1]);
                Task event = new Event(description, eventDate, eventStartTime, eventEndTime);
                return new AddCommand(event);
            } catch (DateTimeParseException e) {
                throw new DukeException("you sussy baka, that's the wrong date format! "
                        + "Enter dd-MM-yyyy HHmm-HHmm");
            }
        }
    }

    private static Command handleTodo(String str, String firstWord) throws IncompleteInputException {
        String subString = str.substring(INPUT_AFTER_TODO).trim();
        if (subString.length() == EMPTY_INPUT) {
            throw new IncompleteInputException(firstWord);
        } else {
            return handleToDoInput(subString);
        }
    }

    private static Command handleDeadline(String str, String firstWord) throws DukeException {
        String subString = str.substring(INPUT_AFTER_DEADLINE).trim();
        if (subString.length() == EMPTY_INPUT) {
            throw new IncompleteInputException(firstWord);
        } else {
            return handleDeadlineInput(subString);
        }
    }

    private static Command handleEvent(String str, String firstWord) throws DukeException {
        String subString = str.substring(INPUT_AFTER_EVENT).trim();
        if (subString.length() == EMPTY_INPUT) {
            throw new IncompleteInputException(firstWord);
        } else {
            return handleEventInput(subString);
        }
    }

    private static Command handleDelete(String[] strings) {
        int listIndex = Integer.parseInt(strings[INDEX_AFTER_DELETE]);
        assert (listIndex > 0) : "This index is sussy, it must be greater than 0!";
        Task taskToBeDeleted = TaskList.TASK_ARRAY_LIST.get(listIndex - 1);
        return new DeleteCommand(taskToBeDeleted);
    }

    private static Command handleFind(String str) {
        String keyword = str.substring(INPUT_AFTER_FIND).trim();
        return new FindCommand(keyword);
    }
}
