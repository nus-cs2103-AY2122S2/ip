package jose;

/**
 * Class to parse user input and return the appropriate command.
 */
public class Parser {
    /**
     * A set of predefined commands.
     */
    public enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND
    }

    /**
     * Parses user input and returns its corresponding command if valid; throws a DukeException otherwise.
     *
     * @param input The given user input.
     * @return A Parser.Command representing the given user input.
     * @throws DukeException If given an unknown or invalid command.
     */
    public Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.equals("list")) {
            return Command.LIST;
        } else {
            String[] task = input.split(" ");
            String command = task[0];
            switch (command) {
            case "mark":
                return Command.MARK;
            case "unmark":
                return Command.UNMARK;
            case "delete":
                return Command.DELETE;
            case "find":
                return Command.FIND;
            case "todo":
                if (task.length > 1) {
                    return Command.TODO;
                } else {
                    throw new DukeException(command + " requires additional info");
                }
            case "deadline":
                if (task.length > 1) {
                    return Command.DEADLINE;
                } else {
                    throw new DukeException(command + " requires additional info");
                }
            case "event":
                if (task.length > 1) {
                    return Command.EVENT;
                } else {
                    throw new DukeException(command + " requires additional info");
                }
            default:
                throw new DukeException("Nani?! No comprende por favor");
            }
        }
    }

    /**
     * Returns an int index as given in the user command.
     *
     * @param input The user command containing an index of a task in the task list.
     * @return An int index of a task.
     */
    public int getIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }
}
