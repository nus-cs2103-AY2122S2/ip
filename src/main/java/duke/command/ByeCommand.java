package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Bye command for Duke.
 */
public class ByeCommand extends Command {
    private static final String BYE = "See ya!";

    /**
     * Bye Command constructor.
     *
     * <p>Calls superclass Command constructor.</>
     *
     * @param key Keyword to call this command.
     */
    public ByeCommand(String key) {
        super(key);
    }

    /**
     * Execution behavior of the bye command.
     *
     * <p>Prints the bye response using the Duke UI.</>
     *
     * @param input User input
     * @param taskList User tasklist.
     * @param storage Storage to store the updated tasklist.
     * @param ui Duke UI to print what the command wants.
     * @throws DukeException Does not throw exception.
     */
    @Override
    public void execute(String input, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        ui.printResponse(BYE);
    }
}
