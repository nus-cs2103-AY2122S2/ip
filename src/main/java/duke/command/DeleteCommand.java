package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.action.Action;

public class DeleteCommand extends Command {
    private final int taskNo;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo - 1;
    }

    /**
     * Executes the deletion of a task from the taskList.
     * @param taskList a list of the current tasks
     * @param ui user interface
     * @param storage file storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Action action = taskList.delete(taskNo);
        System.out.println("Noted. I have removed this task:\n  " +
                action + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Returns false for non-Exit Commands
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
