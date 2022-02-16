package duke.helpers;

import java.util.regex.Pattern;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeException;
import duke.exceptions.InvalidCommandException;

/**
 * Represents a parser which makes sense of user inputs and calls the calls the corresponding commands.
 */
public class Parser {

    /**
     * Determines the type of commands given in the user input.
     *
     * @param s user input.
     * @param command type of command to be checked against.
     * @return boolean that indicates if the user gave the specified command.
     * @throws DukeException If deadline and event commands are missing date and/or time.
     */
    static boolean isCommand(String s, Command command) throws DukeException {
        boolean isValidCommand = false;
        boolean isMissingDesc = false;
        boolean isMissingTime = false;
        switch (command) {
        case BYE:
            isValidCommand = s.equals("bye");
            break;
        case LIST:
            isValidCommand = s.equals("list");
            break;
        case DELETE:
            isValidCommand = Pattern.matches("delete \\d+", s);
            break;
        case TOGGLEMARK:
            isValidCommand = Pattern.matches("mark \\d+|unmark \\d+", s);
            break;
        case TODO:
            isValidCommand = Pattern.matches("todo .+", s);
            isMissingDesc = !isValidCommand && s.equals("todo ");
            break;
        case DEADLINE:
            isValidCommand = Pattern.matches("deadline .+ /by .+", s);
            isMissingDesc = !isValidCommand && Pattern.matches("deadline\\s+|deadline\\s+/by.*", s);
            isMissingTime = !isValidCommand && !isMissingDesc && Pattern.matches("deadline .+", s);
            break;
        case EVENT:
            isValidCommand = Pattern.matches("event .+ /at .+", s);
            isMissingDesc = !isValidCommand && Pattern.matches("event\\s+|event\\s+/at.*", s);
            isMissingTime = !isValidCommand && !isMissingDesc && Pattern.matches("event .+", s);
            break;
        case FIND:
            isValidCommand = isValidCommand = Pattern.matches("find .+", s);
            break;
        default:
            break;
        }
        if (isMissingDesc) {
            throw new EmptyDescriptionException(command.toString());
        }
        if (isMissingTime) {
            throw new EmptyTimeException(command.toString());
        }
        return isValidCommand;
    }

    /**
     * Parse user input and calls TaskList to perform operations accordingly.
     *
     * @param input User input.
     * @throws DukeException If command doesn't exist.
     * @throws java.io.IOException If I/O operations fail or is interrupted.
     */
    public static String parse(String input) {
        String ans = "";
        try {
            if (isCommand(input, Command.BYE)) {
                ans = "Bye. Hope to see you again soon!";
            } else if (isCommand(input, Command.LIST)) {
                ans = TaskList.getTaskList();
            } else if (isCommand(input, Command.DELETE)) {
                ans = TaskList.deleteTask(input, ans);
            } else if (isCommand(input, Command.TOGGLEMARK)) {
                ans = TaskList.toggleMarkTask(input, ans);
            } else if (isCommand(input, Command.TODO)) {
                ans = TaskList.onTodo(input, ans);
            } else if (isCommand(input, Command.DEADLINE)) {
                ans = TaskList.onDeadline(input, ans);
            } else if (isCommand(input, Command.EVENT)) {
                ans = TaskList.onEvent(input, ans);
            } else if (isCommand(input, Command.FIND)) {
                ans = TaskList.getMatchedTasks(input);
            } else {
                throw new InvalidCommandException();
            }
        } catch (DukeException | java.io.IOException e) {
            ans = e.getMessage();
        }
        return ans;
    }
}
