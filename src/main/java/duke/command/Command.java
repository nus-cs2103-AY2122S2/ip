package duke.command;
import duke.exception.DukeException;
import duke.tasklist.TaskList;

/**
 * Represents a command.
 */
public abstract class Command {
    protected String input;

    /**
     * Creates a command.
     *
     * @param input Input from user.
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Executes command on a TaskList.
     *
     * @param taskList The TaskList for command act on.
     * @return String Nothing.
     * @throws DukeException If command not recognized.
     */
    public String callCommand(TaskList taskList) throws DukeException {
        throw new DukeException("Command not supported");
    };
}
