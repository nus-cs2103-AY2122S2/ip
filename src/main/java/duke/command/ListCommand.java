package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String output = taskList.printTaskList();
        return output;
    }
}
