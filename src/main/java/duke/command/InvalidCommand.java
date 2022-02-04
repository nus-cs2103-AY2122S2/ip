package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

/**
 * Command to print error message if the user has given invalid input.
 */
public class InvalidCommand extends Command {

    private String errorMessage;

    /**
     * Constructor for an invalid user command.
     * @param errorMessage the error message describing the invalid command.
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /** {@inheritDoc} */
    @Override
    public String exec(TaskList taskList, Storage storage) {
        return errorMessage;
    }
}
