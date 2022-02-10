package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ErrorCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListAllTasksCommand;
import duke.command.MarkAsDoneCommand;
import duke.command.MarkAsUndoneCommand;
import duke.exceptions.NullDateProvidedException;

/**
 * Parser object that takes in user input and returns a {@code Command} type.
 */
public class Parser {
    private static final String DEADLINE_DATE_KEYWORD = "/by ";
    private static final String EVENT_DATE_KEYWORD = "/at ";
    private TaskList taskList;

    /**
     * Main parse command that takes in user input and returns a {@code Command} type.
     * It also checks for erroneous input.
     *
     * @param input User input.
     * @return A {@code Command} type based on user's input.
     */
    public Command parseCommand(String input) {
        if (input.contains("`")) {
            return new ErrorCommand("\"`\" character is not allowed");
        }

        String[] inputArray = input.split(" ");
        String command = inputArray[0];

        switch (command) {
        case ExitCommand.COMMAND_WORD:
            // Fallthrough
        case "quit":
            return new ExitCommand();
        case ListAllTasksCommand.COMMAND_WORD:
            return new ListAllTasksCommand(taskList);
        case MarkAsDoneCommand.COMMAND_WORD:
            return generateMarkAsDoneCommand(inputArray);
        case MarkAsUndoneCommand.COMMAND_WORD:
            return generateMarkAsUndoneCommand(inputArray);
        case AddTodoCommand.COMMAND_WORD:
            return generateAddTodoCommand(input);
        case AddDeadlineCommand.COMMAND_WORD:
            return generateAddDeadlineCommand(input);
        case AddEventCommand.COMMAND_WORD:
            return generateAddEventCommand(input);
        case DeleteCommand.COMMAND_WORD:
            return generateDeleteCommand(inputArray);
        case FindCommand.COMMAND_WORD:
            return generateFindCommand(input);
        default:
            return new ErrorCommand("Unknown command: " + command);
        }
    }

    private Command generateMarkAsDoneCommand(String[] inputArray) {
        Pair<Boolean, String> result = checkForInvalidIndex(inputArray);
        if (result.first()) {
            return new ErrorCommand(result.second());
        }

        int taskNo = Integer.parseInt(inputArray[1]) - 1;
        assert taskNo >= 0 : "Task number calculation issues";
        return new MarkAsDoneCommand(taskList, taskNo);
    }

    private Command generateMarkAsUndoneCommand(String[] inputArray) {
        Pair<Boolean, String> result = checkForInvalidIndex(inputArray);
        if (result.first()) {
            return new ErrorCommand(result.second());
        }

        int taskNo = Integer.parseInt(inputArray[1]) - 1;
        assert taskNo >= 0 : "Task number calculation issues";
        return new MarkAsUndoneCommand(taskList, taskNo);
    }

    private Command generateAddTodoCommand(String input) {
        String[] inputSplit = input.split(" ", 2);
        if (inputSplit.length < 2) {
            return new ErrorCommand("The description of a todo cannot be empty");
        }
        String description = inputSplit[1];
        return new AddTodoCommand(taskList, description);
    }

    private Command generateAddDeadlineCommand(String input) {
        String[] deadlineStringSplit;
        try {
            boolean hasDateKeyword = input.contains(DEADLINE_DATE_KEYWORD);
            if (!hasDateKeyword) {
                throw new NullDateProvidedException();
            }
            deadlineStringSplit = input.split(DEADLINE_DATE_KEYWORD);
            LocalDate.parse(deadlineStringSplit[1]); // Checking if date valid
        } catch (NullDateProvidedException | IndexOutOfBoundsException | DateTimeParseException e) {
            return new ErrorCommand("Please enter /by followed by a date in this format YYYY-MM-DD");
        }

        // Splitting input to command and deadline
        String deadline = deadlineStringSplit[1];
        String description = deadlineStringSplit[0].split(" ", 2)[1];
        return new AddDeadlineCommand(taskList, description, deadline);
    }

    private Command generateAddEventCommand(String input) {
        String[] deadlineStringSplit;
        try {
            boolean hasDateKeyword = input.contains(EVENT_DATE_KEYWORD);
            if (!hasDateKeyword) {
                throw new NullDateProvidedException();
            }
            deadlineStringSplit = input.split(EVENT_DATE_KEYWORD);
            LocalDate.parse(deadlineStringSplit[1]); // Checking if date valid
        } catch (NullDateProvidedException | IndexOutOfBoundsException | DateTimeParseException e) {
            return new ErrorCommand("Please enter /by followed by a date in this format YYYY-MM-DD");
        }

        // Splitting input to command and deadline
        String deadline = deadlineStringSplit[1];
        String description = deadlineStringSplit[0].split(" ", 2)[1];
        return new AddEventCommand(taskList, description, deadline);
    }

    private Command generateDeleteCommand(String[] inputArray) {
        Pair<Boolean, String> result = checkForInvalidIndex(inputArray);
        if (result.first()) {
            return new ErrorCommand(result.second());
        }

        int taskNo = Integer.parseInt(inputArray[1]) - 1;
        assert taskNo >= 0 : "Task number calculation issues";
        return new DeleteCommand(taskList, taskNo);
    }

    private Command generateFindCommand(String input) {
        String[] inputSplit = input.split(" ", 2);
        if (inputSplit.length < 2) {
            return new ErrorCommand("Search query cannot be empty");
        }
        String searchQuery = inputSplit[1];
        return new FindCommand(taskList, searchQuery);
    }

    private Pair<Boolean, String> checkForInvalidIndex(String[] strArr) {
        try {
            int taskNo = Integer.parseInt(strArr[1]) - 1;
            boolean isIndexLessThanZero = taskNo < 0;
            boolean isIndexLargerThanLength = taskNo >= taskList.size();
            boolean isNotWithinLength = (isIndexLessThanZero || isIndexLargerThanLength);

            if (isNotWithinLength) { // Check if index is out of bounds
                throw new IndexOutOfBoundsException();
            }
        } catch (NumberFormatException e) { // Incorrect number keyed
            return new Pair<>(true, "That's not a number");
        } catch (IndexOutOfBoundsException e) {
            return new Pair<>(true, "Invalid number entered, index out of bounds");
        }
        return new Pair<>(false, "");
    }
}

class Pair<T, V> {
    private final T first;
    private final V second;

    protected Pair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    public T first() {
        return first;
    }

    public V second() {
        return second;
    }
}
