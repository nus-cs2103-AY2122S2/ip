package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;
import duke.command.SortCommand;
import duke.command.UnmarkDoneCommand;
import duke.exception.DukeException;
import duke.task.TaskType;


/**
 * Represents a Parser to make sense of user inputs.
 */
public class Parser {

    private static void checkIfUserInputValid(String userInput) throws DukeException {
        String trimmedInput = userInput.trim();
        assert !trimmedInput.isBlank() : "Input given should not be blank";

        if (checkMissingDescriptionOfInput(trimmedInput)) {
            throw new DukeException(("OOPS!!! The description of a " + trimmedInput + " cannot be empty."));
        }

        if (checkMissingIndexOfInput(trimmedInput)) {
            throw new DukeException(("OOPS!!! Please input the number of the task."));
        }

        if (checkUnknownInput(trimmedInput)) {
            throw new DukeException("\"OOPS!!! I'm sorry, but I don't know what that means :-(\"");
        }
    }
    private static boolean checkUnknownInput(String trimmedInput) {
        return !(trimmedInput.startsWith("list")
                || trimmedInput.startsWith("mark")
                || trimmedInput.startsWith("unmark")
                || trimmedInput.startsWith("delete")
                || trimmedInput.startsWith("todo")
                || trimmedInput.startsWith("event")
                || trimmedInput.startsWith("deadline")
                || trimmedInput.equals("bye")
                || trimmedInput.startsWith("find")
                || trimmedInput.startsWith("sort"));
    }
    private static boolean checkMissingDescriptionOfInput(String trimmedInput) {
        return trimmedInput.equals("todo")
                || trimmedInput.equals("deadline")
                || trimmedInput.equals("event")
                || trimmedInput.equals("find");
    }
    private static boolean checkMissingIndexOfInput(String trimmedInput) {
        return trimmedInput.equals("mark")
                || trimmedInput.equals("unmark")
                || trimmedInput.equals("delete");
    }
    /**
     * Parse user inputs and return a valid Command if user input is valid.
     *
     * @param userInput User input.
     * @return Returns a Command corresponding to the user input.
     * @throws DukeException If user input is invalid.
     */
    public static Command parseUserInput(String userInput) throws DukeException {
        checkIfUserInputValid(userInput);

        if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("mark")) {
            return parseMarkInput(userInput);
        } else if (userInput.startsWith("unmark")) {
            return parseUnmarkInput(userInput);
        } else if (userInput.startsWith("delete")) {
            return parseDeleteInput(userInput);
        } else if (userInput.startsWith("todo")) {
            return parseTodoInput(userInput);
        } else if (userInput.startsWith("deadline")) {
            return parseDeadlineInput(userInput);
        } else if (userInput.startsWith("event")) {
            return parseEventInput(userInput);
        } else if (userInput.startsWith("find")) {
            return parseFindInput(userInput);
        } else if (userInput.equals("sort")) {
            return new SortCommand();
        } else {
            return new ByeCommand();
        }
    }
    private static Command parseMarkInput(String userInput) {
        String str = userInput.substring(5);
        int number = Integer.parseInt(str) - 1;
        return new MarkDoneCommand(number);
    }
    private static Command parseUnmarkInput(String userInput) {
        String str = userInput.substring(7);
        int number = Integer.parseInt(str) - 1;
        return new UnmarkDoneCommand(number);
    }
    private static Command parseDeleteInput(String userInput) {
        String indexStr = userInput.substring(7);
        int index = Integer.parseInt(indexStr);
        return new DeleteCommand(index);
    }
    private static Command parseTodoInput(String userInput) {
        String description = userInput.substring(5);
        TaskType taskType = TaskType.TODO;
        return new AddCommand(taskType, description, null);
    }
    private static Command parseDeadlineInput(String userInput) {
        int start = userInput.indexOf("/");
        String timing = userInput.substring(start + 3);
        String description = userInput.substring(9, start - 1);
        DateTimeFormatter sourceFormat = DateTimeFormatter.ofPattern(" yyyy-MM-dd HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(timing, sourceFormat);
        TaskType taskType = TaskType.DEADLINE;
        return new AddCommand(taskType, description, dateTime);
    }
    private static Command parseEventInput(String userInput) {
        int start = userInput.indexOf("/");
        String timing = userInput.substring(start + 3);
        String description = userInput.substring(6, start - 1);
        DateTimeFormatter sourceFormat = DateTimeFormatter.ofPattern(" yyyy-MM-dd HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(timing, sourceFormat);
        TaskType taskType = TaskType.EVENT;
        return new AddCommand(taskType, description, dateTime);
    }
    private static Command parseFindInput(String userInput) {
        String description = userInput.substring(5);
        return new FindCommand(description);
    }
}
