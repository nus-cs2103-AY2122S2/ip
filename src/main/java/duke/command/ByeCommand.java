package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Bye command for Duke.
 */
public class ByeCommand extends Command {
    public static final String BYE_COMMAND = "bye";

    private static final String BYE = "See ya!";

    /**
     * Bye Command constructor.
     *
     * <p>Calls superclass Command constructor.</p>
     *
     * @param key Keyword to call this command.
     */
    public ByeCommand() {
        super(BYE_COMMAND);
    }

    /**
     * Execution behavior of the bye command.
     *
     * <p>Prints the bye response using the Duke UI.</p>
     *
     * @param input User input
     * @param taskList User tasklist.
     * @param storage Storage to store the updated tasklist.
     * @return Bye response.
     * @throws DukeException Does not throw exception.
     */
    @Override
    public String execute(String input, TaskList taskList, Storage storage) throws DukeException {
        return BYE;
    }
}
