package duke.parser;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.NumberedCommand;
import duke.command.InvalidCommand;
import duke.command.AddCommand;
import duke.command.ClearCommand;
import duke.command.ListCommand;
import duke.command.UndoCommand;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a parser object that formats and retrieves
 * information from users' text inputs.
 */
public class Parser {

    /**
     * Returns the first word of a string.
     *
     * @param input The given input string.
     * @return The first word of the input.
     */
    public static String getCommand(String input) {
        assert !input.trim().equals("") : "input cannot be empty";
        int index = input.indexOf(' ');
        return (index >= 0) ? input.substring(0, index) : input;
    }

    /**
     * Returns the integer value after a single word.
     *
     * @param input The input string containing the number and a word.
     * @return The integer value after the word.
     */
    public static int getIndex(String input) {
        try {
            input = input.trim();
            String separator = " ";
            int pos = input.indexOf(separator);
            int res = Integer.parseInt(input.substring(pos + 1).trim());
            return res;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Returns a Task object parsed from a text input.
     *
     * @param input The input string containing the task object in text.
     * @return The task object parsed from the input.
     */
    public static Task textToTask(String input) {
        char identifier = input.charAt(0);
        Task result;

        switch(identifier) {
        case 'T':
            int statusOfTodo = Character.getNumericValue(input.charAt(2));
            result = Todo.createTodo(statusOfTodo, input.substring(4));
            break;
        case 'D':
            int statusOfDl = Character.getNumericValue(input.charAt(2));
            int separatorOfDl = input.lastIndexOf('/');
            String descriptionOfDl = input.substring(4, separatorOfDl);
            String dateOfDl = input.substring(separatorOfDl + 1);
            result = Deadline.createDeadline(statusOfDl, descriptionOfDl, dateOfDl);
            break;
        case 'E':
            int statusOfEv = Character.getNumericValue(input.charAt(2));
            int separatorOfEv = input.lastIndexOf('/');
            String descriptionOfEv = input.substring(4, separatorOfEv);
            String dateOfEv = input.substring(separatorOfEv + 1);
            result = Event.createEvent(statusOfEv, descriptionOfEv, dateOfEv);
            break;
        default:
            result = new Task("Invalid");
            break;
        }
        return result;
    }

    /**
     * Returns the appropriate action to take after parsing a text
     * input.
     *
     * @param text The user's input text.
     * @return A Command object that will carry out the appropriate
     * action.
     */
    public Command parse(String text) {
        String input = text.trim();
        String command = getCommand(input);
        Command commandToExecute;
        switch(command) {
        case "list":
            commandToExecute = new ListCommand();
            break;
        case "mark":
        case "unmark":
        case "delete":
            commandToExecute = new NumberedCommand(getIndex(input), command, input);
            break;
        case "todo":
        case "deadline":
        case "event":
            commandToExecute = new AddCommand(command, input);
            break;
        case "find":
            commandToExecute = new FindCommand(input);
            break;
        case "clear":
            commandToExecute = new ClearCommand();
            break;
        case "bye":
            commandToExecute = new ExitCommand();
            break;
        case "undo":
            commandToExecute = new UndoCommand();
            break;
        default:
            commandToExecute = new InvalidCommand();
            break;
        }
        return commandToExecute;
    }

}
