package duke;

import task.TaskList;

/**
 * The DukeException class deals with identifying and throwing Exceptions unique to Duke.
 */
public class DukeException extends Exception {
    public DukeException() {

    }

    public DukeException(String message) {
        super(message);
    }

    /**
     * Validates the inputs based on the command the user input and throws an error if there is invalid input
     *
     * @param command             The command that the user inputs.
     * @param commandSplitBySpace The user command separated by space.
     * @param tasks               ArrayList of Tasks.
     * @throws DukeException If the inputs are not valid.
     */
    public void validateInputs(String command, String[] commandSplitBySpace, TaskList tasks) throws DukeException {
        switch (commandSplitBySpace[0]) {
        case "list":
        case "bye":
            break;
        case "find":
            if (commandSplitBySpace.length == 1) {
                throw new DukeException("☹ OOPS!!! The keyword to be searched cannot be empty.");
            }
            break;
        case "mark":
        case "unmark":
        case "delete":
            if (commandSplitBySpace.length > 1) {
                if (!tasks.hasTask(Integer.parseInt(commandSplitBySpace[1]) - 1)) {
                    throw new DukeException("☹ OOPS!!! The task to be "
                            + commandSplitBySpace[0] + "ed does not exist.");
                }
            } else {
                throw new DukeException("☹ OOPS!!! The task to be "
                        + commandSplitBySpace[0] + "ed has to be indicated.");
            }
            break;
        case "todo":
            if (commandSplitBySpace.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case "deadline":
            if (commandSplitBySpace.length > 1) {
                int indexOfBy = command.indexOf("/by");
                if (indexOfBy == -1) {
                    throw new DukeException("☹ OOPS!!! The by of a deadline cannot be empty.");
                }
            } else {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            break;
        case "event":
            if (commandSplitBySpace.length > 1) {
                int indexOfAt = command.indexOf("/at");
                if (indexOfAt == -1) {
                    throw new DukeException("☹ OOPS!!! The at of a deadline cannot be empty.");
                }
            } else {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
