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
    private static final String ADD_DEADLINE_FORMAT = "add deadline [content] /by yyyy-MM-dd HH:mm";
    private static final String ADD_EVENT_FORMAT = "add event [content] /at yyyy-MM-dd HH:mm";
    private static final String MARK_FORMAT = "mark [task number]";
    private static final String UNMARK_FORMAT = "unmark [task number]";
    private static final String DELETE_FORMAT = "delete [task number]";

    /**
     * Returns a Command which is the result of parsing the user input.
     *
     * @param input a String which is the user input.
     * @return a Command.
     */
    public static Command parse(String input) {
        if (input.toLowerCase().equals("bye")) {
            return new ExitCommand();
        } else if (input.toLowerCase().equals("list")) {
            return new ListCommand();
        } else if (input.length() < 3) {
            return new IncorrectCommand(INVALID_COMMAND_WARNING);
        } else if (input.substring(0, 3).toLowerCase().equals("add")) {
            return handleAdd(input.substring(3));
        } else if (input.length() < 4) {
            return new IncorrectCommand(INVALID_COMMAND_WARNING);
        } else if (input.substring(0, 4).toLowerCase().equals("find")) {
            return handleFind(input.substring(4));
        } else if (input.substring(0, 4).toLowerCase().equals("mark")) {
            return handleMark(input.substring(4));
        } else if (input.length() < 6) {
            return new IncorrectCommand(INVALID_COMMAND_WARNING);
        } else if (input.substring(0, 6).toLowerCase().equals("unmark")) {
            return handleUnmark(input.substring(6));
        } else if (input.substring(0, 6).toLowerCase().equals("delete")) {
            return handleDelete(input.substring(6));
        } else {
            return new IncorrectCommand(INVALID_COMMAND_WARNING);
        }
    }

    /**
     * Parses the contents of an 'add' command and returns the appropriate Command object.
     *
     * @param input a String which represents the contents of the 'add' command.
     * @return an appropriate Command object.
     */
    private static Command handleAdd(String input) {
        if (input.isBlank()) {
            return new IncorrectCommand("Oops! Please specify the task you wish to add!");
        } else if (input.charAt(0) != ' ') {
            return new IncorrectCommand(INVALID_COMMAND_WARNING);
        } else {
            String taskType = input.split(" ")[1].toLowerCase();
            switch (taskType) {
            case "todo":
                return handleTodo(input.substring(6));
            case "event":
                return handleEvent(input.substring(7));
            case "deadline":
                return handleDeadline(input.substring(10));
            default:
                return new IncorrectCommand("Oops! Please enter a valid task type!");
            }
        }
    }

    /**
     * Parses the contents of a 'mark' command and returns the appropriate Command object.
     *
     * @param index a String which should contain the index of the Task to be mark as done.
     * @return an appropriate Command object.
     */
    private static Command handleMark(String index) {
        if (index.isBlank()) {
            return new IncorrectCommand(FORMAT_WARNING + MARK_FORMAT);
        } else {
            try {
                int i = Integer.parseInt(index.substring(1));
                return new MarkCommand(i);
            } catch (NumberFormatException nfe) {
                return new IncorrectCommand(FORMAT_WARNING + MARK_FORMAT);
            }
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
            return new IncorrectCommand(FORMAT_WARNING + UNMARK_FORMAT);
        } else {
            try {
                int i = Integer.parseInt(index.substring(1));
                return new UnmarkCommand(i);
            } catch (NumberFormatException nfe) {
                return new IncorrectCommand(FORMAT_WARNING + UNMARK_FORMAT);
            }
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
            return new IncorrectCommand(FORMAT_WARNING + DELETE_FORMAT);
        } else {
            try {
                int i = Integer.parseInt(index.substring(1));
                return new DeleteCommand(i);
            } catch (NumberFormatException nfe) {
                return new IncorrectCommand(FORMAT_WARNING + DELETE_FORMAT);
            }
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

    private static Command handleFind(String findKeyWords) {
        if (findKeyWords.isBlank()) {
            return new IncorrectCommand(FORMAT_WARNING + "find [key word(s)]");
        } else {
            return new FindCommand(findKeyWords.trim());
        }
    }
}
