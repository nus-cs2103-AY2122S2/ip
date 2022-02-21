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
import duke.command.PostponeTaskCommand;
import duke.exceptions.NullDateProvidedException;
import duke.exceptions.TaskNoDateException;
import duke.misc.Pair;
import duke.taskobjects.Task;
import duke.taskobjects.TaskWithDate;

/**
 * Parser object that takes in user input and returns a {@code Command} type.
 */
public class Parser {
    private static final String DEADLINE_DATE_KEYWORD = "/by ";
    private static final String EVENT_DATE_KEYWORD = "/at ";
    private TaskList taskList;

    /**
     * Parses user input and returns the appropriate {@code Command} and result.
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
        case PostponeTaskCommand.COMMAND_WORD:
            return generatePostponeCommand(inputArray);
        default:
            return new ErrorCommand("Unknown command: " + command);
        }
    }

    /**
     * Sets the task list used in the current Parser instance.
     *
     * Used for {@code Command}s and certain result printing.
     * @param taskList Task list used for modification or reference when {@code Command}s are executed.
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
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
        LocalDate deadline;
        try {
            boolean hasDateKeyword = input.contains(DEADLINE_DATE_KEYWORD);
            if (!hasDateKeyword) {
                throw new NullDateProvidedException();
            }
            deadlineStringSplit = input.split(DEADLINE_DATE_KEYWORD);
            deadline = LocalDate.parse(deadlineStringSplit[1]); // Checking if date valid
        } catch (NullDateProvidedException | IndexOutOfBoundsException | DateTimeParseException e) {
            return new ErrorCommand("Please enter /by followed by a date in this format YYYY-MM-DD");
        }

        // Splitting input to command and deadline
        String description = deadlineStringSplit[0].split(" ", 2)[1];
        return new AddDeadlineCommand(taskList, description, deadline);
    }

    private Command generateAddEventCommand(String input) {
        String[] deadlineStringSplit;
        LocalDate deadline;
        try {
            boolean hasDateKeyword = input.contains(EVENT_DATE_KEYWORD);
            if (!hasDateKeyword) {
                throw new NullDateProvidedException();
            }
            deadlineStringSplit = input.split(EVENT_DATE_KEYWORD);
            deadline = LocalDate.parse(deadlineStringSplit[1]); // Checking if date valid
        } catch (NullDateProvidedException | IndexOutOfBoundsException | DateTimeParseException e) {
            return new ErrorCommand("Please enter /by followed by a date in this format YYYY-MM-DD");
        }

        // Splitting input to command and deadline
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

    private Command generatePostponeCommand(String[] inputArray) {
        PostponeCommandCheckResults results = checkPostponeCommandInputs(inputArray);
        if (!results.isSuccessful()) {
            return new ErrorCommand(results.getErrorMessage());
        }

        return new PostponeTaskCommand(taskList, results.getTaskNo(), results.getNewDate());
    }

    private PostponeCommandCheckResults checkPostponeCommandInputs(String[] inputArray) {
        int taskNo;
        LocalDate newDate;

        // Checking for 3 parameters
        if (inputArray.length != 3) {
            return new PostponeCommandCheckResults("Invalid arguments entered");
        }

        // Check for invalid index
        Pair<Boolean, String> result = checkForInvalidIndex(inputArray);
        if (result.first()) {
            return new PostponeCommandCheckResults(result.second());
        }
        taskNo = Integer.parseInt(inputArray[1]) - 1;

        try {
            // Check if task has date
            Task currentTask = taskList.get(taskNo);
            if (!(currentTask instanceof TaskWithDate)) {
                throw new TaskNoDateException();
            }

            // Check if date is valid
            newDate = LocalDate.parse(inputArray[2]);
        } catch (TaskNoDateException e) {
            return new PostponeCommandCheckResults("The selected task does not have a date");
        } catch (DateTimeParseException e) {
            return new PostponeCommandCheckResults("Please enter date in the format YYYY-MM-DD");
        }
        // Checking for valid results
        assert taskNo != -1 : "Task number incorrect";
        assert newDate != null : "New Date not instantiated correctly";
        return new PostponeCommandCheckResults(taskNo, newDate);
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

class PostponeCommandCheckResults {
    private final boolean isSuccessful;
    private final String errorMessage;
    private final int taskNo;
    private final LocalDate newDate;

    /**
     * Creates a PostPoneCommandCheckResults object.
     * Constructor for PostponeCommandCheckResults when isSuccessful = false.
     *
     * @param errorMessage Error message
     */
    public PostponeCommandCheckResults(String errorMessage) {
        this.isSuccessful = false;
        this.errorMessage = errorMessage;
        taskNo = -1;
        newDate = null;
    }

    /**
     * Creates a PostPoneCommandCheckResults object.
     * Constructor for PostponeCommandCheckResults when isSuccessful = true.
     *
     * @param taskNo Task number.
     * @param newDate New date to be appended to the task.
     */
    public PostponeCommandCheckResults(int taskNo, LocalDate newDate) {
        this.isSuccessful = true;
        this.errorMessage = "";
        this.taskNo = taskNo;
        this.newDate = newDate;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getTaskNo() {
        return taskNo;
    }

    public LocalDate getNewDate() {
        return newDate;
    }
}
