package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents the parse function of the program. Deals with parsing user input.
 */
public class Parser {

    /**
     * Returns the Command parsed from the user input.
     *
     * @param fullInput User input.
     * @return Command to execute further operations.
     * @throws DukeException If user input is invalid.
     */
    public static Command parse(String fullInput) throws DukeException {
        String[] inputArr = fullInput.split(" ");
        String command = inputArr[0].toLowerCase();

        Command parsedCommand;
        switch(command) {
        case "list":
            return new ListCommand();
        case "delete":
            Integer taskNum = getTaskNumber(inputArr);
            parsedCommand = new DeleteCommand(taskNum);
            break;
        case "mark":
            taskNum = getTaskNumber(inputArr);
            parsedCommand = new MarkCommand(taskNum);
            break;
        case "unmark":
            taskNum = getTaskNumber(inputArr);
            parsedCommand = new UnmarkCommand(taskNum);
            break;
        case "todo":
            String description = getDescription(inputArr, fullInput, command);
            parsedCommand = new AddCommand(new Todo(description));
            break;
        case "deadline":
            description = getDescription(inputArr, fullInput, command);
            String[] descrArr = splitDescriptionByKeyword(description, " /by ", command);
            Task task = new Deadline(descrArr[0], parseDateTime(descrArr[1]));
            parsedCommand = new AddCommand(task);
            break;
        case "event":
            description = getDescription(inputArr, fullInput, command);
            descrArr = splitDescriptionByKeyword(description, " /at ", command);
            task = new Event(descrArr[0], parseDateTime(descrArr[1]));
            parsedCommand = new AddCommand(task);
            break;
        case "find":
            String keyword = getKeyword(inputArr);
            parsedCommand = new FindCommand(keyword);
            break;
        case "bye":
            parsedCommand = new ExitCommand();
            break;
        default:
            parsedCommand = new InvalidCommand();
        }
        return parsedCommand;
    }

    /**
     * Returns the task number provided by user.
     *
     * @param inputArr The user input split by spaces.
     * @return Integer task number.
     * @throws DukeException If task number is not provided.
     */
    public static Integer getTaskNumber(String[] inputArr) throws DukeException {
        if (inputArr.length == 1) {
            throw new DukeException("Please specify a task number!");
        }
        return Integer.parseInt(inputArr[1]);
    }

    /**
     * Returns the String description provided in the user input.
     *
     * @param inputArr The user input split by spaces.
     * @param command The command that the description belongs to.
     * @return String description.
     * @throws DukeException If the description is not provided.
     */
    public static String getDescription(String[] inputArr, String fullInput, String command) throws DukeException {
        if (inputArr.length == 1) {
            throw new DukeException("Oops, an " + command + " description cannot be left empty!");
        }
        return fullInput.replace(command + " ", "");
    }

    /**
     * Returns the String keyword provided in the user input.
     *
     * @param inputArr The user input split by spaces.
     * @returns String keyword.
     * @throws DukeException If a keyword is not provided or if more than one keyword is provided.
     */
    public static String getKeyword(String[] inputArr) throws DukeException {
        if (inputArr.length == 1) {
            throw new DukeException("Please specify a keyword!");
        } else if (inputArr.length > 2) {
            throw new DukeException("Please specify only one keyword!");
        }
        return inputArr[1];
    }

    /**
     * Splits a String description by a given keyword.
     * Returns a String[] where the first String is the task description and the second String is the date time.
     *
     * @param description The task description.
     * @param keyword The keyword to be found in the description to check if a date/time is specified.
     * @param taskType The type of the task.
     * @return String[] containing description and datetime.
     * @throws DukeException If keyword is not given in description.
     */
    public static String[] splitDescriptionByKeyword(String description, String keyword, String taskType)
            throws DukeException {
        if (!description.contains(keyword)) {
            throw new DukeException("Oops, please use " + keyword
                    + " to set a date and time for this " + taskType + "!");
        }
        return description.split(" /by ");
    }

    /**
     * Returns LocalDateTime object.
     *
     * @param dt The string of format yyyy-MM-dd HH:mm
     * @return LocalDateTime.
     * @throws DateTimeParseException If given string is not in the specified format.
     */
    public static LocalDateTime parseDateTime(String dt) throws DukeException {
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(dt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date/time format.\n    "
                    + "Please use the following format: yyyy-mm-dd HH:mm");
        }
        return dateTime;
    }
}
