package duke.operations;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.MarkCommand;
import duke.command.PrintCommand;
import duke.command.UnmarkCommand;
import duke.exceptions.DukeException;
import duke.exceptions.IncompleteInputException;
import duke.exceptions.UnknownInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a parser for Duke. It deals with making sense of the user command.
 */
public class Parser {

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
        // Create a String array to read various functions
        String[] strs = input.split(" ");

        // Store first word as variable
        String firstWord = strs[0];

        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return new PrintCommand();
        } else if (firstWord.equalsIgnoreCase("mark")) {
            return handleMarkInput(strs[1]);
        } else if (firstWord.equalsIgnoreCase("unmark")) {
            return handleUnmarkInput(strs[1]);
        } else if (firstWord.equalsIgnoreCase("todo")) {
            String subString = input.substring(4).trim(); // take the remaining of the input String after "todo"
            if (subString.length() == 0) { // Check for empty description
                throw new IncompleteInputException(firstWord);
            } else {
                return handleToDoInput(subString);
            }
        } else if (firstWord.equalsIgnoreCase("deadline")) {
            String subString = input.substring(8).trim(); // take the remaining of the input String after "deadline"
            if (subString.length() == 0) { // Check for empty description
                throw new IncompleteInputException(firstWord);
            } else {
                return handleDeadlineInput(subString);
            }
        } else if (firstWord.equalsIgnoreCase("event")) {
            String subString = input.substring(5).trim(); // take the remaining of the input String after "event"
            if (subString.length() == 0) { // Check for empty description
                throw new IncompleteInputException(firstWord);
            } else {
                return handleEventInput(subString);
            }
        } else if (firstWord.equalsIgnoreCase("delete")) {
            int listIndex = Integer.parseInt(strs[1]); // retrieve the index after delete
            Task taskToBeDeleted = TaskList.TASK_ARRAY_LIST.get(listIndex - 1);
            return new DeleteCommand(taskToBeDeleted);
        } else if (firstWord.equalsIgnoreCase("find")) {
            String keyword = input.substring(4).trim(); // take the remaining of the input String
            return new FindCommand(keyword);
        } else {
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
        Ui.line();
        System.out.println("     Remember to do your task!");
        return new AddCommand(toDo);
    }

    private static Command handleDeadlineInput(String str) throws DukeException {
        // breaks the string into 2 parts, description, date and time.
        String[] inputArgs = str.split(" /");
        if (!inputArgs[1].substring(0, 3).equals("by ")) {
            throw new UnknownInputException();
        } else {
            String deadlineDateTime = inputArgs[1].substring(3); // retrieves the String after '/by'
            String[] dateTimeArgs = deadlineDateTime.split(" ");
            try {
                LocalDate deadlineDate = convertStringToLocalDate(dateTimeArgs[0]);
                LocalTime deadlineTime = convertStringToLocalTime(dateTimeArgs[1]);
                Task deadline = new Deadline(inputArgs[0], deadlineDate, deadlineTime);
                Ui.line();
                System.out.println("     This task is on a timer!");
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
            String eventDateTime = inputArgs[1].substring(3); // retrieves the String after '/at'
            String[] dateTimeArgs = eventDateTime.split(" ");
            try {
                LocalDate eventDate = convertStringToLocalDate(dateTimeArgs[0]);
                String eventTime = dateTimeArgs[1];
                String[] splitEventTimes = eventTime.split("-");
                LocalTime eventStartTime = convertStringToLocalTime(splitEventTimes[0]);
                LocalTime eventEndTime = convertStringToLocalTime(splitEventTimes[1]);
                Task event = new Event(inputArgs[0], eventDate, eventStartTime, eventEndTime);
                Ui.line();
                System.out.println("     Emergency event on this date!");
                return new AddCommand(event);
            } catch (DateTimeParseException e) {
                throw new DukeException("you sussy baka, that's the wrong date format! "
                        + "Enter dd-MM-yyyy HHmm-HHmm");
            }
        }
    }
}
