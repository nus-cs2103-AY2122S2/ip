package Duke.command;

import Duke.exception.DukeException;

import Duke.util.TaskList;
import Duke.util.Storage;
import Duke.util.Ui;

/**
 * This ListCommand class will show a list of added tasks when executed.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Executes command by adding task into Duke.Duke.util.TaskList.
     * @param taskList List of tasks
     * @param ui       Ui provided
     * @param storage  Saved history
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.printTaskList();
    }
}
