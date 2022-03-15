package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.FindDateCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SortCommand;
import duke.main.DukeException;
import duke.task.DeadLine;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Parser to read user commands and ensure that it is syntactically and semantically valid.
 *
 */
public class Parser {
    private static final String DESCRIPTION_EMPTY = "The description of a %s cannot be empty.\n";
    private static final String FILL_PROPER_INPUT = "Fill in proper input to find.\n";
    private static final String FILL_PROPER_INPUT_MARK = "Fill in proper integer for marking/unmarking.\n";
    private static final String FILL_PROPER_INPUT_DELETE = "Fill in proper integer for deletion.\n";
    private static final String NO_SUCH_COMMAND = "I'm sorry, but I don't know what that means\n";
    private static final String SORT_KEYWORD_INVALID = "Keyword for sorting is invalid.\n";
    private static final String SORT_ORDER_INVALID = "Cannot determine from command whether ascending or descending.\n";

    /**
     * Returns the command from parsing user's instructions.
     *
     * @param cmd The instruction of the user.
     * @return Command to be executed by Duke.
     * @throws DukeException If there is a syntactic or semantic error.
     */
    public static Command parse(String cmd) throws DukeException {
        String[] input = cmd.split(" ");
        switch (input[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
        case "deadline":
        case "event":
            return Parser.prepareAdd(input);
        case "mark":
        case "unmark":
            return Parser.prepareMark(input);
        case "delete":
            return Parser.prepareDelete(input);
        case "findDate":
        case "find":
            return Parser.prepareFind(input);
        case "sort":
            return Parser.prepareSort(input);
        default:
            throw new DukeException(NO_SUCH_COMMAND);
        }
    }

    private static Command prepareSort(String[] input) throws DukeException {
        if (input.length != 3 && input.length != 2) {
            throw new duke.main.DukeException(FILL_PROPER_INPUT);
        }

        String keyword = input[1].toLowerCase();
        if (!keyword.equals("task") && !keyword.equals("mark") && !keyword.equals("date")) {
            throw new duke.main.DukeException(SORT_KEYWORD_INVALID);
        }

        if (input.length == 2) {
            return new SortCommand(input[1]);
        }

        String order = input[2].toLowerCase();
        if (!order.equals("asc") && !order.equals("desc")) {
            throw new duke.main.DukeException(SORT_ORDER_INVALID);
        }
        Boolean isAscending = order.equals("asc");
        return new SortCommand(input[1], isAscending);
    }

    private static Command prepareFind(String[] input) throws DukeException {
        if (input.length != 2) {
            throw new duke.main.DukeException(FILL_PROPER_INPUT);
        }

        if (input[0].equals("find")) {
            return new FindCommand(input[1]);
        } else {
            assert input[0].equals("findDate");
            return new FindDateCommand(input[1]);
        }
    }

    private static Command prepareAdd(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException(String.format(DESCRIPTION_EMPTY, input[0]));
        }

        String taskName = getTaskString(input);
        if (input[0].equals("todo")) {
            return new AddCommand(new ToDo(taskName));
        } else if (input[0].equals("deadline")) {
            return new AddCommand(new DeadLine(taskName, input[input.length - 2], input[input.length - 1]));
        } else if (input[0].equals("event")) {
            return new AddCommand(new Event(taskName, input[input.length - 3],
                    input[input.length - 2], input[input.length - 1]));
        } else {
            throw new DukeException(NO_SUCH_COMMAND);
        }
    }

    private static Command prepareMark(String[] input) throws DukeException {
        if (input.length != 2) {
            throw new DukeException(FILL_PROPER_INPUT_MARK);
        }
        if (input[0].equals("mark")) {
            return new MarkCommand(Integer.parseInt(input[1]), true);
        } else {
            assert input[0].equals("unmark");
            return new MarkCommand(Integer.parseInt(input[1]), false);
        }
    }

    private static Command prepareDelete(String[] input) throws DukeException {
        if (input.length != 2) {
            throw new DukeException(FILL_PROPER_INPUT_DELETE);
        }
        return new DeleteCommand(Integer.parseInt(input[1]));
    }

    private static String getTaskString(String[] input) {
        StringBuilder obj = new StringBuilder("");
        for (int i = 1; i < input.length; i++) {
            if (input[i].charAt(0) == '/') {
                break;
            }
            obj.append(input[i]);
            obj.append(" ");
        }
        obj.setLength(obj.length() - 1);
        return obj.toString();
    }
}
