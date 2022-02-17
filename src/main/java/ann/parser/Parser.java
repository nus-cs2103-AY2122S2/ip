package ann.parser;

import ann.commands.AddCommand;
import ann.commands.Command;
import ann.commands.DeleteCommand;
import ann.commands.ExitCommand;
import ann.commands.FindCommand;
import ann.commands.IncorrectCommand;
import ann.commands.ListCommand;
import ann.commands.MarkCommand;
import ann.commands.UnmarkCommand;
import ann.data.InputPattern;
import ann.data.tasks.Deadline;
import ann.data.tasks.Event;
import ann.data.tasks.TaskType;

/**
 * Represents a parser to parse user input into appropriate commands.
 *
 * @author Hong Anh
 * @version 0.1
 */
public class Parser {
    private static final String INVALID_COMMAND_WARNING = "Oops! Please enter a valid command!";
    private static final String FORMAT_WARNING = "Oops! Please use the following format:\n";
    private static final String MISSING_ADD_CONTENT_WARNING = "Oops! Please specify the task you wish to add!";
    private static final String MISSING_ADD_TASK_TYPE_WARNING = "Oops! Please enter a valid task type!";
    private static final String ADD_DEADLINE_FORMAT = AddCommand.KEYWORD + " " + Deadline.INPUT_FORMAT;
    private static final String ADD_EVENT_FORMAT = AddCommand.KEYWORD + " " + Event.INPUT_FORMAT;

    /**
     * Returns a Command which is the result of parsing the user input.
     *
     * @param input a String which is the user input.
     * @return a Command.
     */
    public static Command parse(String input) {
        if (isExitCommand(input)) {
            return new ExitCommand();
        } else if (isListCommand(input)) {
            return new ListCommand();
        } else if (isAddCommand(input)) {
            return handleAdd(getSubstringAfterKeyword(input, AddCommand.KEYWORD));
        } else if (isFindCommand(input)) {
            return handleFind(getSubstringAfterKeyword(input, FindCommand.KEYWORD));
        } else if (isMarkCommand(input)) {
            return handleMark(getSubstringAfterKeyword(input, MarkCommand.KEYWORD));
        } else if (isUnmarkCommand(input)) {
            return handleUnmark(getSubstringAfterKeyword(input, UnmarkCommand.KEYWORD));
        } else if (isDeleteCommand(input)) {
            return handleDelete(getSubstringAfterKeyword(input, DeleteCommand.KEYWORD));
        } else {
            return new IncorrectCommand(INVALID_COMMAND_WARNING);
        }
    }
    private static boolean isExitCommand(String input) {
        return input.equalsIgnoreCase(ExitCommand.KEYWORD);
    }
    private static boolean isListCommand(String input) {
        return input.equalsIgnoreCase(ListCommand.KEYWORD);
    }
    private static boolean isAddCommand(String input) {
        return hasCommandKeyword(input, AddCommand.KEYWORD);
    }
    private static boolean isMarkCommand(String input) {
        return hasCommandKeyword(input, MarkCommand.KEYWORD);
    }
    private static boolean isUnmarkCommand(String input) {
        return hasCommandKeyword(input, UnmarkCommand.KEYWORD);
    }
    private static boolean isDeleteCommand(String input) {
        return hasCommandKeyword(input, DeleteCommand.KEYWORD);
    }
    private static boolean isFindCommand(String input) {
        return hasCommandKeyword(input, FindCommand.KEYWORD);
    }
    private static boolean hasCommandKeyword(String input, String ck) {
        return input.equals(ck) || input.startsWith(ck + " ");
    }
    private static String getSubstringAfterKeyword(String input, String kw) {
        return input.equals(kw) ? "" : input.substring(kw.length() + 1);
    }

    /**
     * Parses the contents of an 'add' command and returns the appropriate Command object.
     *
     * @param input a String which represents the contents of the 'add' command.
     * @return an appropriate Command object.
     */
    private static Command handleAdd(String input) {
        if (input.isBlank()) {
            return new IncorrectCommand(MISSING_ADD_CONTENT_WARNING);
        } else if (isTodo(input)) {
            return handleTodo(getSubstringAfterKeyword(input, TaskType.TODO.getKeyword()));
        } else if (isDeadline(input)) {
            return handleDeadline(getSubstringAfterKeyword(input, TaskType.DEADLINE.getKeyword()));
        } else if (isEvent(input)) {
            return handleEvent(getSubstringAfterKeyword(input, TaskType.EVENT.getKeyword()));
        } else {
            return new IncorrectCommand(MISSING_ADD_TASK_TYPE_WARNING);
        }
    }
    private static boolean isTodo(String input) {
        return isTaskType(input, TaskType.TODO);
    }
    private static boolean isDeadline(String input) {
        return isTaskType(input, TaskType.DEADLINE);
    }
    private static boolean isEvent(String input) {
        return isTaskType(input, TaskType.EVENT);
    }
    private static boolean isTaskType(String input, TaskType tt) {
        return input.equals(tt.getKeyword()) || input.startsWith(tt.getKeyword() + " ");
    }

    /**
     * Parses the contents of a 'mark' command and returns the appropriate Command object.
     *
     * @param index a String which should contain the index of the Task to be mark as done.
     * @return an appropriate Command object.
     */
    private static Command handleMark(String index) {
        if (index.isBlank()) {
            return new IncorrectCommand(FORMAT_WARNING + MarkCommand.FORMAT);
        } else if (index.matches("^\\d+")) {
            int i = Integer.parseInt(index);
            return new MarkCommand(i);
        } else {
            return new IncorrectCommand(FORMAT_WARNING + MarkCommand.FORMAT);
        }
    }

    /**
     * Parses the contents of an 'unmark' command and returns the appropriate Command object.
     *
     * @param index a String which should contain the index of the Task to be mark as not done.
     * @return an appropriate Command object.
     */
    private static Command handleUnmark(String index) {
        if (index.isBlank()) {
            return new IncorrectCommand(FORMAT_WARNING + UnmarkCommand.FORMAT);
        } else if (index.matches("^\\d+")) {
            int i = Integer.parseInt(index);
            return new UnmarkCommand(i);
        } else {
            return new IncorrectCommand(FORMAT_WARNING + UnmarkCommand.FORMAT);
        }
    }

    /**
     * Parses the contents of a 'delete' command and returns the appropriate Command object.
     *
     * @param index a String which should contain the index of the Task to be deleted.
     * @return an appropriate Command object.
     */
    private static Command handleDelete(String index) {
        if (index.isBlank()) {
            return new IncorrectCommand(FORMAT_WARNING + DeleteCommand.FORMAT);
        } else if (index.matches("^\\d+")) {
            int i = Integer.parseInt(index);
            return new DeleteCommand(i);
        } else {
            return new IncorrectCommand(FORMAT_WARNING + DeleteCommand.FORMAT);
        }
    }

    /**
     * Parses the contents of an 'add todo' command and returns the appropriate Command object.
     *
     * @param todoContent a String which should contain the contents of the Task to be added.
     * @return an appropriate Command object.
     */
    private static Command handleTodo(String todoContent) {
        if (todoContent.isBlank()) {
            return new IncorrectCommand("Oops! Please add a description for the todo!");
        } else {
            return new AddCommand(TaskType.TODO, new String[] {todoContent});
        }
    }

    /**
     * Parses the contents of an 'add event' command and returns the appropriate Command object.
     *
     * @param eventComponents a String which should contain the information of the Event to be added.
     * @return an appropriate Command object.
     */
    private static Command handleEvent(String eventComponents) {
        if (eventComponents.isBlank()) {
            return new IncorrectCommand(FORMAT_WARNING + ADD_EVENT_FORMAT);
        } else {
            String[] eventComponentsArray = eventComponents.split(" /at ");
            if (eventComponentsArray.length <= 1) {
                return new IncorrectCommand(FORMAT_WARNING + ADD_EVENT_FORMAT);
            } else if (eventComponentsArray[0].isBlank()) {
                return new IncorrectCommand("Oops! Please add a description for the event!");
            } else if (eventComponentsArray[1].isBlank()) {
                return new IncorrectCommand("Oops! Please add a date and time for the event!");
            } else if (!InputPattern.isValidDateTimeString(eventComponentsArray[1])) {
                return new IncorrectCommand(FORMAT_WARNING + ADD_EVENT_FORMAT);
            } else {
                return new AddCommand(TaskType.EVENT, eventComponentsArray);
            }
        }
    }

    /**
     * Parses the contents of a 'add deadline' command and returns the appropriate Command object.
     *
     * @param deadlineComponents a String which should contain the information of the Deadline to be added.
     * @return an appropriate Command object.
     */
    private static Command handleDeadline(String deadlineComponents) {
        if (deadlineComponents.isBlank()) {
            return new IncorrectCommand(FORMAT_WARNING + ADD_DEADLINE_FORMAT);
        } else {
            String[] deadlineComponentsArray = deadlineComponents.split(" /by ");
            if (deadlineComponentsArray.length <= 1) {
                return new IncorrectCommand(FORMAT_WARNING + ADD_DEADLINE_FORMAT);
            } else if (deadlineComponentsArray[0].isBlank()) {
                return new IncorrectCommand("Oops! Please add a description for the deadline!");
            } else if (deadlineComponentsArray[1].isBlank()) {
                return new IncorrectCommand("Oops! Please add a deadline for the deadline!");
            } else if (!InputPattern.isValidDateTimeString(deadlineComponentsArray[1])) {
                return new IncorrectCommand(FORMAT_WARNING + ADD_DEADLINE_FORMAT);
            } else {
                return new AddCommand(TaskType.DEADLINE, deadlineComponentsArray);
            }
        }
    }

    private static Command handleFind(String keyWords) {
        if (keyWords.isBlank()) {
            return new IncorrectCommand(FORMAT_WARNING + FindCommand.FORMAT);
        } else {
            return new FindCommand(keyWords.trim());
        }
    }
}
