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
            default:
                throw new DukeException(String.format("Sorry, the command '%s' is not supported.", commandString));
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please specify dates in the yyyy-mm-dd format");
        }
    }

    private static Todo makeTodo(String userInput) throws DukeException {
        if (userInput.strip().equals("")) {
            throw new DukeException("Please enter a description for the todo task.");
        }
        return new Todo(userInput);
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
        boolean hasNoDescription = taskDetails[0].strip().equals("");
        boolean hasNoDate = taskDetails[1].strip().equals("");
        return isTooShort || hasNoDescription || hasNoDate;
    }

    private static int convertIndex(String indexString, int taskListLength) throws DukeException {
        if (indexString.strip().equals("")) {
            throw new DukeException("Please specify a task.");
        }
        int index;
        try {
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify a task using its index in the task list.");
        }
        if (index < 1 || index > taskListLength) {
            throw new DukeException("Please specify a valid task index.");
        }
        return index;
    }
}
