package kidsnd274.duke;

import kidsnd274.duke.command.*;
import kidsnd274.duke.exceptions.NullDateProvidedException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parser object that takes in user input and returns a {@code Command} type.
 */
public class Parser {
    private TaskList taskList;

    /**
     * Main parse command that takes in user input and returns a {@code Command} type.
     *
     * It also checks for erroneous input
     * @param input User input
     * @return A {@code Command} type based on user's input
     */
    public Command parseCommand(String input) {
        if (input.contains("`")) {
            return new ErrorCommand("\"`\" character is not allowed");
        }

        String[] inputArray = input.split(" ");
        String command = inputArray[0];

        if (command.equals(ExitCommand.COMMAND_WORD) || command.equals("quit")) {
            return new ExitCommand();

        } else if (command.equals(ListAllTasksCommand.COMMAND_WORD)) {
            return new ListAllTasksCommand(taskList);

        } else if (command.equals(MarkAsDoneCommand.COMMAND_WORD)) {
            Pair<Boolean, String> result = checkForInvalidIndex(inputArray);
            if (result.first) {
                return new ErrorCommand(result.second);
            }

            int taskNo = Integer.parseInt(inputArray[1]) - 1;
            return new MarkAsDoneCommand(taskList, taskNo);
        } else if (command.equals(MarkAsUndoneCommand.COMMAND_WORD)) {
            Pair<Boolean, String> result = checkForInvalidIndex(inputArray);
            if (result.first) {
                return new ErrorCommand(result.second);
            }

            int taskNo = Integer.parseInt(inputArray[1]) - 1;
            return new MarkAsUndoneCommand(taskList, taskNo);
        } else if (command.equals(AddTodoCommand.COMMAND_WORD)) {
            String[] inputSplit = input.split(" ", 2);
            if (inputSplit.length < 2) {
                return new ErrorCommand("The description of a todo cannot be empty");
            }
            String description = inputSplit[1];
            return new AddTodoCommand(taskList, description);
        } else if (command.equals(AddDeadlineCommand.COMMAND_WORD)) {
            String[] deadlineStringSplit;

            // Checking for date
            try {
                if (!input.contains("/by ")) {
                    throw new NullDateProvidedException();
                } else {
                    deadlineStringSplit = input.split("/by ");
                    LocalDate.parse(deadlineStringSplit[1]); // Checking if date valid
                }
            } catch (NullDateProvidedException e) { // No date typed in
                return new ErrorCommand("Please enter /by followed by a date in this format YYYY-MM-DD");

            } catch (IndexOutOfBoundsException | DateTimeParseException e) { // Date typed wrongly
                return new ErrorCommand("Please enter /by followed by a date in this format YYYY-MM-DD");

            }

            // Splitting input to command and deadline
            String deadline = deadlineStringSplit[1];
            String description = deadlineStringSplit[0].split(" ", 2)[1];
            return new AddDeadlineCommand(taskList, description, deadline);

        } else if (command.equals(AddEventCommand.COMMAND_WORD)) {
            String[] deadlineStringSplit;

            // Checking for date
            try {
                if (!input.contains("/at ")) {
                    throw new NullDateProvidedException();
                } else {
                    deadlineStringSplit = input.split("/at ");
                    LocalDate.parse(deadlineStringSplit[1]); // Checking if date valid
                }
            } catch (NullDateProvidedException e) { // No date typed in
                return new ErrorCommand("Please enter /by followed by a date in this format YYYY-MM-DD");

            } catch (IndexOutOfBoundsException | DateTimeParseException e) { // Date typed wrongly
                return new ErrorCommand("Please enter /by followed by a date in this format YYYY-MM-DD");

            }

            // Splitting input to command and deadline
            String deadline = deadlineStringSplit[1];
            String description = deadlineStringSplit[0].split(" ", 2)[1];
            return new AddEventCommand(taskList, description, deadline);

        } else if (command.equals(DeleteCommand.COMMAND_WORD)) {
            Pair<Boolean, String> result = checkForInvalidIndex(inputArray);
            if (result.first) {
                return new ErrorCommand(result.second);
            }

            int taskNo = Integer.parseInt(inputArray[1]) - 1;
            return new DeleteCommand(taskList, taskNo);
        }

        return new ErrorCommand("Unknown command: " + command);
    }

    /**
     * Sets the task list used in the current Parser instance
     *
     * Used for {@code Command}s and certain result printing.
     * @param taskList Task list used for modification or reference when {@code Command}s are executed.
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    private Pair<Boolean, String> checkForInvalidIndex(String[] strArr) {
        try {
            int taskNo = Integer.parseInt(strArr[1]) - 1;
            if (taskNo < 0 || taskNo >= taskList.size()) { // Check if index is out of bounds
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
    public T first;
    public V second;

    protected Pair(T first, V second) {
        this.first = first;
        this.second = second;
    }
}