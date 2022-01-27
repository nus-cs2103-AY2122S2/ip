package DukeHelpers;

import Exceptions.DukeException;
import Exceptions.EmptyDescriptionException;
import Exceptions.EmptyTimeException;
import Exceptions.InvalidCommandException;
import java.util.regex.Pattern;
import Commands.Command;


public class Parser {

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
        }
        if (missingDesc) { throw new EmptyDescriptionException(command.toString()); }
        if (missingTime) { throw new EmptyTimeException(command.toString()); }
        return res;
    }

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
                TaskList.toggleMarkTask(ans, input);
            } else if (isCommand(input, Command.TODO)) {
                TaskList.onTodo(ans, input);
            } else if (isCommand(input, Command.DEADLINE)) {
                TaskList.onDeadline(ans, input);
            } else if (isCommand(input, Command.EVENT)) {
                TaskList.onEvent(ans, input);
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
