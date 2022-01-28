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
            if (command.equals("mark")) {
                return Command.MARK;
            } else if (command.equals("unmark")) {
                return Command.UNMARK;
            } else if (command.equals("delete")) {
                return Command.DELETE;
            } else if (command.equals("find")) {
                return Command.FIND;
            } else {
                if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    if (task.length > 1) {
                        if (command.equals("todo")) {
                            return Command.TODO;
                        } else if (command.equals("deadline")) {
                            return Command.DEADLINE;
                        } else {
                            return Command.EVENT;
                        }
                    } else {
                        throw new DukeException(command + " requires additional info");
                    }
                } else {
                    throw new DukeException("Nani?! No comprende por favor");
                }
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
