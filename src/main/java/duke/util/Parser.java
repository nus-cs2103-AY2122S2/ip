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

    public Parser() {}

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
        String description = fullInput.replace(command + " ", "");

        try {
            switch(command) {
            case "list":
                return new ListCommand();
            case "delete":
                if (descriptionExists(inputArr, "Please specify a task number!")) {
                    int taskNum = Integer.parseInt(description);
                    return new DeleteCommand(taskNum);
                }
                break;
            case "mark":
                if (descriptionExists(inputArr, "Please specify a task number!")) {
                    int taskNum = Integer.parseInt(description);
                    return new MarkCommand(taskNum);
                }
                break;
            case "unmark":
                if (descriptionExists(inputArr, "Please specify a task number!")) {
                    int taskNum = Integer.parseInt(description);
                    return new UnmarkCommand(taskNum);
                }
                break;
            case "todo":
                if (descriptionExists(inputArr, "Oops, a todo description cannot be left empty!")) {
                    Task task = new Todo(description);
                    return new AddCommand(task);
                }
                break;
            case "deadline":
                if (descriptionExists(inputArr, "Oops, a deadline description cannot be left empty!")) {
                    String[] descrArr = description.split(" /by ");
                    if (keywordExists(descrArr, "/by", "deadline")) {
                        LocalDateTime dateTime = parseDateTime(descrArr[1]);
                        Task task = new Deadline(descrArr[0], dateTime);
                        return new AddCommand(task);
                    }
                }
                break;
            case "event":
                if (descriptionExists(inputArr, "Oops, an event description cannot be left empty!")) {
                    String[] descrArr = description.split(" /at ");
                    if (keywordExists(descrArr, "/at", "event")) {
                        LocalDateTime dateTime = parseDateTime(descrArr[1]);
                        Task task = new Event(descrArr[0], dateTime);
                        return new AddCommand(task);
                    }
                }
                break;
            case "find":
                if (descriptionExists(inputArr, "Please specify a keyword!")) {
                    if (inputArr.length > 2) {
                        throw new DukeException("Please specify only one keyword!");
                    } else {
                        return new FindCommand(description);
                    }
                }
                break;
            case "bye":
                return new ExitCommand();
            default:
                throw new DukeException("Oh no! I don't understand what that means...");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date/time format.\n    "
                    + "Please use the following format: yyyy-mm-dd HH:mm");
        }
        return new InvalidCommand();
    }

    /**
     * Returns a boolean indicating whether there is a task description provided in the user input.
     *
     * @param inputArr The user input split by spaces.
     * @param errorMessage The error message to be printed if description does not exist.
     * @return True if there is a description, false otherwise.
     * @throws DukeException If the description is not provided.
     */
    public static boolean descriptionExists(String[] inputArr, String errorMessage) throws DukeException {
        if (inputArr.length == 1) {
            throw new DukeException(errorMessage);
        } else {
            return true;
        }
    }

    /**
     * Returns a boolean indicating whether there is a given keyword provided in the task description.
     *
     * @param descrArr The description split by the keyword.
     * @param keyword The keyword to be found in the description.
     * @param taskType The type of the task (e.g. deadline, event)
     * @return True if keyword can be found, false otherwise.
     * @throws DukeException If keyword is not found in description.
     */
    public static boolean keywordExists(String[] descrArr, String keyword, String taskType) throws DukeException {
        if (descrArr.length == 1) {
            throw new DukeException("Oops, please use " + keyword
                    + " to set a date and time for this " + taskType + "!");
        } else {
            return true;
        }
    }

    /**
     * Returns LocalDateTime.
     *
     * @param dt The string of format yyyy-MM-dd HH:mm
     * @return LocalDateTime.
     * @throws DateTimeParseException If given string is not in the specified format.
     */
    public static LocalDateTime parseDateTime(String dt) throws DateTimeParseException {
        return LocalDateTime.parse(dt,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
