package duke.command;

import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

import duke.action.Action;

/**
 * An extension from Command.
 * The DeleteCommand is tasked with the deletion
 * of a specified task from the given taskList.
 * The int variable, taskNo, is specific to the class.
 */
public class DeleteCommand extends Command {
    private final int taskNo;

    /**
     * Constructs a new DeleteCommand object with
     * the variable taskNo.
     * @param taskNo arraylist index location of the task within the taskList
     */
    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo - 1;
    }

    /**
     * Returns a string describing the result of
     * the deletion of task from the taskList.
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
