import java.util.ArrayList;

public class DukeException extends Exception {
    public DukeException() {

    }

    public DukeException(String message) {
        super(message);
    }

    /**
     * Validates the inputs based on the command the user input and throws an error if there is invalid input
     * @param command String that the user inputs
     * @param commandSplitBySpace String[] from the command the user inputs separated by space
     * @param tasks ArrayList of Tasks
     * @throws DukeException
     */
    public void validateInputs(String command, String[] commandSplitBySpace, ArrayList<Task> tasks) throws DukeException {
        if (commandSplitBySpace[0].equals("mark")) {
            if (commandSplitBySpace.length > 1) {
                if (!(tasks.size() != 0 && Integer.parseInt(commandSplitBySpace[1]) - 1 >= 0 &&
                        Integer.parseInt(commandSplitBySpace[1]) - 1 < tasks.size())) {
                    throw new DukeException("☹ OOPS!!! The task to be marked does not exist.");
                }
            } else {
                throw new DukeException("☹ OOPS!!! The task to be marked has to be indicated.");
            }
        } else if (commandSplitBySpace[0].equals("unmark")) {
            if (commandSplitBySpace.length > 1) {
                if (!(tasks.size() != 0 && Integer.parseInt(commandSplitBySpace[1]) - 1 >= 0 &&
                        Integer.parseInt(commandSplitBySpace[1]) - 1 < tasks.size())) {
                    throw new DukeException("☹ OOPS!!! The task to be unmarked does not exist.");
                }
            } else {
                throw new DukeException("☹ OOPS!!! The task to be unmarked has to be indicated.");
            }
        } else if (commandSplitBySpace[0].equals("todo")) {
            if (commandSplitBySpace.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (commandSplitBySpace[0].equals("deadline")) {
            if (commandSplitBySpace.length > 1) {
                int indexOfBy = command.indexOf("/by");
                if (indexOfBy == -1) {
                    throw new DukeException("☹ OOPS!!! The by of a deadline cannot be empty.");
                }
            } else {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
        } else if (commandSplitBySpace[0].equals("event")) {
            if (commandSplitBySpace.length > 1) {
                int indexOfAt = command.indexOf("/at");
                if (indexOfAt == -1) {
                    throw new DukeException("☹ OOPS!!! The at of a deadline cannot be empty.");
                }
            } else {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
        } else if (commandSplitBySpace[0].equals("delete")) {
            if (commandSplitBySpace.length > 1) {
                if (!(tasks.size() != 0 && Integer.parseInt(commandSplitBySpace[1]) - 1 >= 0 &&
                        Integer.parseInt(commandSplitBySpace[1]) - 1 < tasks.size())) {
                    throw new DukeException("☹ OOPS!!! The task to be deleted does not exist.");
                }
            } else {
                throw new DukeException("☹ OOPS!!! The task to be deleted has to be indicated.");
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
