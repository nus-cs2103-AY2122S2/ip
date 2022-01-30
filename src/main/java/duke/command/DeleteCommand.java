package duke.command;

import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Action action = taskList.delete(taskNo);
        return new StringBuilder().append("Noted. I have removed this task:\n  ")
                .append(action).append("\nNow you have ").append(taskList.size()).append(" tasks in the list.")
                .toString();
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
