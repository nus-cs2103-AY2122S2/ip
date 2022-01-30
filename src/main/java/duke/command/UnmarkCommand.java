package duke.command;

import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {

    private final int taskNo;

    public UnmarkCommand(int taskNo) {
        this.taskNo = taskNo - 1;
    }

    /**
     * Executes a method that sets the status of the task
     * as undone.
     * @param taskList a list of the current tasks
     * @param ui user interface
     * @param storage file storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.setUnDone(taskNo);
        String statement = "Ok, I have marked this task as not done yet:\n  ";
        return statement + taskList.getAction(taskNo);
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
