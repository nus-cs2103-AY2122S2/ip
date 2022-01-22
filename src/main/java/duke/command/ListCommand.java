package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ListCommand extends Command {

    /**
     * Executes a method from taskList which prints
     * out the contents from the taskList with numeric
     * notation.
     * @param taskList a list of the current tasks
     * @param ui user interface
     * @param storage file storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.listOut();
    }

    /**
     * Returns false for non-Exit commands.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
