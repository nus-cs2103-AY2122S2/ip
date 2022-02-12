package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.AddTaskCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.RemindCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Encapsulates a parser for Duke. It makes sense
 * of the inputs supplied by the user.
 */
public class Parser {

    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String LIST = "list";
    private static final String DELETE = "delete";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String BYE = "bye";
    private static final String FIND = "find";
    private static final String REMIND = "remind";

    /**
     * Parses user inputs to produce commands understood by Duke.
     * @param userInput the user input
     * @param taskListLength the length of Duke's task list.
     * @return a Command object that executes the user's command.
     * @throws DukeException for invalid user inputs.
     */
    public static Command parse(String userInput, int taskListLength) throws DukeException {
        String[] inputArr = userInput.split(" ", 2);
        String commandString = inputArr[0];
        String details = inputArr.length > 1 ? inputArr[1] : "";
        try {
            switch (commandString) {
            case TODO:
                return new AddTaskCommand(makeTodo(details));
            case DEADLINE:
                return new AddTaskCommand(makeDeadline(details));
            case EVENT:
                return new AddTaskCommand(makeEvent(details));
            case MARK:
                return new MarkCommand(convertIndex(details, taskListLength));
            case UNMARK:
                return new UnmarkCommand(convertIndex(details, taskListLength));
            case DELETE:
                return new DeleteCommand(convertIndex(details, taskListLength));
            case LIST:
                return new ListCommand();
            case BYE:
                return new ByeCommand();
            case FIND:
                return new FindCommand(details);
            case REMIND:
                return new RemindCommand(parseNumberOfDays(details));
            default:
                throw new DukeException(String.format("Sorry, the command '%s' is not supported.", commandString));
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please specify dates in the yyyy-mm-dd format");
        }
    }

    private static Todo makeTodo(String description) throws DukeException {
        if (isEmptyString(description)) {
            throw new DukeException("Please enter a description for the todo task.");
        }
        return new Todo(description);
    }

    private static Deadline makeDeadline(String userInput) throws DukeException {
        String[] deadlineDetails = userInput.split(" /by ", 2);

        if (isInvalidDetails(deadlineDetails)) {
            throw new DukeException("Please specify a deadline task as\n"
                    + "deadline [description] /by [date in yyyy-mm-dd format].");
        }

        return new Deadline(deadlineDetails[0], LocalDate.parse(deadlineDetails[1]));
    }

    private static Event makeEvent(String userInput) throws DukeException {
        String[] eventDetails = userInput.split(" /at ", 2);

        if (isInvalidDetails(eventDetails)) {
            throw new DukeException("Please specify an event task as\n"
                    + "event [description] /by [date in yyyy-mm-dd format].");
        }

        return new Event(eventDetails[0], LocalDate.parse(eventDetails[1]));
    }

    private static boolean isInvalidDetails(String[] taskDetails) {
        boolean isTooShort = taskDetails.length == 1;
        boolean hasNoDescription = isEmptyString(taskDetails[0]);
        boolean hasNoDate = isEmptyString(taskDetails[1]);
        return isTooShort || hasNoDescription || hasNoDate;
    }

    private static int convertIndex(String indexString, int taskListLength) throws DukeException {
        if (isEmptyString(indexString)) {
            throw new DukeException("Please specify a task.");
        }
        checkValidNumber(indexString);
        int index = Integer.parseInt(indexString);
        if (index < 1 || index > taskListLength) {
            throw new DukeException("Please specify a valid task index.");
        }
        return index;
    }

    private static int parseNumberOfDays(String userInput) throws DukeException {
        if (isEmptyString(userInput)) {
            return RemindCommand.DEFAULT_NUM_DAYS;
        }
        checkValidNumber(userInput);
        int numberOfDays = Integer.parseInt(userInput);
        if (numberOfDays < 0) {
            throw new DukeException("Please specify a positive number of days from today for the reminder.");
        }
        return numberOfDays;
    }

    private static boolean isEmptyString(String userInput) {
        return userInput.strip().equals("");
    }

    private static void checkValidNumber(String userInput) throws DukeException {
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify a task using its index in the task list.");
        }
    }
}
