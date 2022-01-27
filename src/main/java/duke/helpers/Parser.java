package duke.helpers;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeException;
import duke.exceptions.InvalidCommandException;
import duke.commands.Command;

import java.util.regex.Pattern;

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
        boolean res = false;
        boolean missingDesc = false;
        boolean missingTime = false;
        switch (command) {
        case BYE:
            res = s.equals("bye");
            break;
        case LIST:
            res = s.equals("list");
            break;
        case DELETE:
            res = Pattern.matches("delete \\d+", s);
            break;
        case TOGGLEMARK:
            res = Pattern.matches("mark \\d+|unmark \\d+", s);
            break;
        case TODO:
            res = Pattern.matches("todo .+", s);
            missingDesc = !res && s.equals("todo ");
            break;
        case DEADLINE:
            res = Pattern.matches("deadline .+ /by .+", s);
            missingDesc = !res && Pattern.matches("deadline\\s+|deadline\\s+/by.*", s);
            missingTime = !res && !missingDesc && Pattern.matches("deadline .+", s);
            break;
        case EVENT:
            res = Pattern.matches("event .+ /at .+", s);
            missingDesc = !res && Pattern.matches("event\\s+|event\\s+/at.*", s);
            missingTime = !res && !missingDesc && Pattern.matches("event .+", s);
            break;
            case FIND:
                res = res = Pattern.matches("find .+", s);
                break;
        }
        if (missingDesc) { throw new EmptyDescriptionException(command.toString()); }
        if (missingTime) { throw new EmptyTimeException(command.toString()); }
        return res;
    }

    /**
     * Parse user input and calls TaskList to perform operations accordingly.
     *
     * @param input User input.
     * @throws DukeException If command doesn't exist.
     * @throws java.io.IOException If I/O operations fail or is interrupted.
     */
    public static void parse(String input) {
        String ans = "\t";
        try {
            if (isCommand(input, Command.BYE)) {
                System.out.println("\tBye. Hope to see you again soon!");
            } else if (isCommand(input, Command.LIST)) {
                TaskList.getTaskList();
            } else if (isCommand(input, Command.DELETE)) {
                TaskList.deleteTask(input, ans);
            } else if (isCommand(input, Command.TOGGLEMARK)) {
                TaskList.toggleMarkTask(input, ans);
            } else if (isCommand(input, Command.TODO)) {
                TaskList.onTodo(input, ans);
            } else if (isCommand(input, Command.DEADLINE)) {
                TaskList.onDeadline(input, ans);
            } else if (isCommand(input, Command.EVENT)) {
                TaskList.onEvent(input, ans);
            } else if (isCommand(input, Command.FIND)) {
                TaskList.getMatchedTasks(input);
            } else {
                throw new InvalidCommandException();
            }
        } catch (DukeException | java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
