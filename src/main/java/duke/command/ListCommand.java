package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command to list and print the current task list.
 */
public class ListCommand extends Command {

    /**
     * Constructor to list all tasks in teh task list.
     */
    public ListCommand(){}

    /** {@inheritDoc} */
    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) {
        String printStr = taskList.list();
        ui.print(printStr);
        return true;
    }
}
