package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;


/**
 * The DukeException class identifies and throws Exceptions unique to Duke.
 */
public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    public DukeException() {}

    /**
     * The method invalidChecker checks for any invalid input the user might have entered and throws an exception
     * indicating the error.
     * @param input information entered by the user.
     * @param tasks the current number of tasks that is being tracked.
     */
    public void invalidChecker (String[] input, int tasks) throws DukeException {
        assert !(input.length <= 0);
        switch (input[0]) {
        case "bye":
        case "list":
            break;
        case "mark":
        case "unmark":
        case "delete":
            checkValid(input, tasks);
            break;
        case "find":
            checkValidFind(input);
            break;
        case "todo":
            checkValidTodo(input);
            break;
        case "deadline":
            checkValidDeadline(input);
            break;
        case "event":
            checkValidEvent(input);
            break;
        default:
            throw new DukeException("MEOW!!! I don't know what that means :-(");
        }
    }

    /**
     * Checks if the user has entered a invalid format mark, unmark and delete.
     * @param input information entered by the user.
     * @param tasks the current number of tasks that is being tracked.
     */
    private void checkValid(String[] input, int tasks) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("MEOW!!! Please enter a task number.");
        }
        int taskNum = Integer.parseInt(input[1]);
        if (!(taskNum <= tasks && taskNum > 0)) {
            throw new DukeException("MEOW!!! Task number does not exist.");
        }
    }

    /**
     * Checks if the user has entered a invalid format Find.
     * @param input information entered by the user.
     */
    private void checkValidFind(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("MEOW!!! Please enter a task to find.");
        } else {
            String[] task = input[1].split(" ");
            if (task.length > 1) {
                throw new DukeException("MEOW!!! Sorry you can only search for single words.");
            }
        }
    }

    /**
     * Checks if the user has entered a invalid format Todo.
     * @param input information entered by the user.
     */
    private void checkValidTodo(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("MEOW!!! The description of a task cannot be empty.");
        }
    }

    /**
     * Checks if the user has entered a invalid format Deadline.
     * @param input information entered by the user.
     */
    private void checkValidDeadline(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("MEOW!!! The description of a task cannot be empty.");
        } else {
            String[] dateTime = input[1].split("/by ", 2);
            if (dateTime[0].equals("")) {
                throw new DukeException("MEOW!!! The description of a deadline cannot be empty.");
            } else if (dateTime.length == 1) {
                throw new DukeException("MEOW!!! Please enter a deadline for the task using /by.");
            }
            checkValidDate(dateTime[1]);
        }
    }

    /**
     * Checks if the user has entered a invalid format Event.
     * @param input information entered by the user.
     */
    private void checkValidEvent(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("MEOW!!! The description of a task cannot be empty.");
        } else {
            String[] dateTime = input[1].split("/at ", 2);
            if (dateTime[0].equals("")) {
                throw new DukeException("MEOW!!! The description of a event cannot be empty.");
            } else if (dateTime.length == 1) {
                throw new DukeException("MEOW!!! Please enter a timeframe for the task using /at.");
            }
            checkValidDate(dateTime[1]);
        }
    }

    /**
     * Checks if the user has entered a invalid format for the date and time.
     * @param dateTime contains the date and time entered by the user.
     */
    private void checkValidDate(String dateTime) throws DukeException {
        LocalDateTime d1 = LocalDateTime.now();
        try {
            LocalDateTime d2 = LocalDateTime.parse(dateTime, new DateTimeFormatterBuilder()
                            .parseCaseInsensitive()
                            .appendPattern("yyyy-MM-dd hha")
                            .toFormatter());
            if (d2.isBefore(d1)) {
                throw new DukeException("MEOW!!! Please enter a valid date from "
                        + d1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hha")) + " and onwards.");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("MEOW!!! Please enter a valid date and time in the format yyyy-mm-dd hha "
                    + "(Example: 2022-10-10 10pm)");
        }
    }
}
