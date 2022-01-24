package duke;

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


import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

    public static Command parse(String userInput, int taskListLength) throws DukeException {
        String[] inputArr = userInput.split(" ", 2);
        String commandString = inputArr[0];
        String details = inputArr.length > 1 ? inputArr[1] : "";
        try {
            switch (commandString) {
            case TODO:
                if (details.strip().equals("")) {
                    throw new DukeException("Please enter a description for the todo task.");
                }
                return new AddTaskCommand(new Todo(details));
            case DEADLINE:
                String[] deadlineInputs = details.split(" /by ", 2);
                if (deadlineInputs.length == 1 || deadlineInputs[1].strip().equals("")
                        || deadlineInputs[0].strip().equals("")) {
                    throw new DukeException("Please specify a deadline task as\n" +
                            "deadline [description] /by [date in yyyy-mm-dd format].");
                }
                return new AddTaskCommand(new Deadline(deadlineInputs[0], LocalDate.parse(deadlineInputs[1])));
            case EVENT:
                String[] eventInputs = details.split(" /at ", 2);
                if (eventInputs.length == 1 || eventInputs[1].strip().equals("")
                        || eventInputs[0].strip().equals("")) {
                    throw new DukeException("Please specify an event task as\n" +
                            "event [description] /by [date in yyyy-mm-dd format].");
                }
                return new AddTaskCommand(new Event(eventInputs[0], LocalDate.parse(eventInputs[1])));
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
