package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * List command for Duke to list out all current tasks.
 */
public class ListCommand extends Command {
    /**
     * List Command constructor.
     *
     * <p>Calls superclass Command constructor.</>
     *
     * @param key Keyword to call this command.
     */
    public ListCommand(String key) {
        super(key);
    }

    /**
     * Execution behavior of the list command.
     *
     * <p>Prints out the current tasks in the list using the Duke UI.</>
     *
     * @param input User input
     * @param taskList User tasklist.
     * @param storage Storage to store the updated tasklist.
     * @param ui Duke UI to print what the command wants.
     * @throws DukeException
     */
    @Override
    public void execute(String input, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        ui.printResponse(("Here are the tasks in your list: \n" + taskList.getTaskListStr()));
    }
}
