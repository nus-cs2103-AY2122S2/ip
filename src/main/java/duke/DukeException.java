package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.*;

/**
 * The DukeException class identifies and throws Exceptions unique to Duke.
 */
public class DukeException extends Exception {
    String[] validInputs = new String[] {"deadline", "todo", "event", };
    List<String> inputList = new ArrayList<>(Arrays.asList(validInputs));
    String[] validInputs2 = new String[] {"mark", "unmark", "delete"};
    List<String> inputList2 = new ArrayList<>(Arrays.asList(validInputs2));
    String[] validInputs3 = new String[] {"list", "bye"};
    List<String> inputList3 = new ArrayList<>(Arrays.asList(validInputs3));

    public DukeException(String message) {
        super(message);
    }

    public DukeException() {}

    /**
     * The method invalidChecker checks for any invalid input the user might have entered and throws an exception
     * indicating the error.
     * @param input contains information entered by the user.
     * @param tasks indicates the current number of tasks that is being tracked.
     */
    public void invalidChecker (String[] input, int tasks) throws DukeException {
        switch (input[0]) {
            case "bye":
            case "list":
                break;
            case "mark":
            case "unmark":
            case "delete":
                if (input.length == 1) {
                    throw new DukeException("☹ OOPS!!! Please enter a task number.");
                }
                int taskNum = Integer.parseInt(input[1]);
                if (!(taskNum <= tasks && taskNum > 0)) {
                    throw new DukeException("☹ OOPS!!! Task number does not exist.");
                }else {
                    String[] dateTime  = tempList[1].split("/by ", 2);
                    if (dateTime[0].equals("")) {
                        throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                    } else if (dateTime.length == 1) {
                        throw new DukeException("☹ OOPS!!! Please enter a deadline for the task using /by.");
                    }
                    isValidDate(dateTime[1]);
                }
                break;
            case "find":
                if (tempList.length == 1) {
                    throw new DukeException("☹ OOPS!!! Please enter a task to find.");
                } else {
                    String[] task  = tempList[1].split(" ");
                    if (task.length > 1) {
                        throw new DukeException("☹ OOPS!!! Sorry you can only search for single words.");
                    }
                }
                break;
            case "todo":
                if (input.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
                }
                break;
            case "deadline":
                if (input.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
                } else {
                    String[] dateTime  = input[1].split("/by ", 2);
                    if (dateTime[0].equals("")) {
                        throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                    } else if (dateTime.length == 1) {
                        throw new DukeException("☹ OOPS!!! Please enter a deadline for the task using /by.");
                    }
                    isValidDate(dateTime[1]);
                }
                break;
            case "event":
                if (input.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
                } else {
                    String[] dateTime  = input[1].split("/at ", 2);
                    if (dateTime[0].equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    } else if (dateTime.length == 1) {
                        throw new DukeException("☹ OOPS!!! Please enter a timeframe for the task using /at.");
                    }
                    isValidDate(dateTime[1]);
                }
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * The method isValidDate checks if the user has entered a invalid format for the date and time.
     * @param dateTime contains the date and time entered by the user.
     */
    private void isValidDate(String dateTime) throws DukeException {
        LocalDateTime d1 = LocalDateTime.now();
        try {
            LocalDateTime d2 = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd hha"));
            if (d2.isBefore(d1)) {
                throw new DukeException("☹ OOPS!!! Please enter a valid date from "
                        + d1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hha")) + " and onwards.");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Please enter a valid date and time in the format yyyy-mm-dd hha " +
                    "(Example: 2022-10-10 10PM)");
        }
    }
}
