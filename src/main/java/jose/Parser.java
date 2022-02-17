package jose;

/**
 * Class to parse user input and return the appropriate command.
 */
public class Parser {
    /**
     * A set of predefined commands.
     */
    public enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND, PRIORITY, HELP
    }

    /**
     * Parses user input and returns its corresponding command if valid; throws a DukeException otherwise.
     *
     * @param str The given user input.
     * @return A Command enum representing the given user input.
     * @throws DukeException If given an unknown or invalid command.
     */
    public Command parse(String str) throws DukeException {
        String input = str.trim();

        if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.equals("list")) {
            return Command.LIST;
        } else if (input.equals("help")) {
            return Command.HELP;
        } else {
            String[] taskInfo = input.split(" ");
            String command = taskInfo[0];

            if (taskInfo.length < 2) {
                throw new DukeException("Incorrect formatido. Type 'help' for help compadre.");
            }
            assert taskInfo.length > 1 : "taskInfo should contain at least 2 strings";
            switch (command) {
            case "priority":
                return Command.PRIORITY;
            case "mark":
                return Command.MARK;
            case "unmark":
                return Command.UNMARK;
            case "delete":
                return Command.DELETE;
            case "find":
                return Command.FIND;
            case "todo":
                return Command.TODO;
            case "deadline":
                return Command.DEADLINE;
            case "event":
                return Command.EVENT;
            default:
                throw new DukeException("Nani?! No comprende por favor. Type 'help' for help homer.");
            }
        }
    }

    /**
     * Returns an int index as given in the user command.
     *
     * @param input The user command containing an index of a task in the task list.
     * @return An int index of a task.
     * @throws DukeException If integer is invalid.
     */
    public int getIndex(String input) throws DukeException {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Enter a valid integer index por favor.");
        }
    }

    /**
     * Returns the priority as a string.
     *
     * @param input The user command containing a priority.
     * @return A string representation of a priority level.
     * @throws DukeException If priority is not found.
     */
    public String getPriority(String input) throws DukeException {
        try {
            return input.split(" ")[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Incorrect format. Type 'help' to view list of commands.");
        }
    }

    /**
     * Returns a string query as given in the user command.
     *
     * @param input The user command containing a query.
     * @return An string query.
     * @throws DukeException If the input does not contain a query
     */
    public String getQuery(String input) throws DukeException {
        try {
            return input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Search query missing. Format: 'find [query]'.");
        }
    }
}
