package duke.command;

import duke.DukeException;
import duke.common.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * A class that delete task object from a task list.
 */
public class DeleteCommand extends Command {
    private static final boolean IS_EXIT = false;
    private final int taskId;

    /**
     * Creates a DeleteCommand instance with a task id.
     *
     * @param taskId The id of the targeted task.
     */
    public DeleteCommand(int taskId) {
        super(IS_EXIT);
        this.taskId = taskId;
    }

    /**
     * Deletes a task object from the task list and
     * show the execution message on the GUI.
     *
     * @param tasks The current task list.
     * @param storage The current storage of Duke.
     * @return The string of the GUI message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            String task = tasks.deleteTask(taskId);
            storage.saveAllTasks(tasks);
            return TextUi.showExecutionMessage(Messages.MESSAGE_DELETE, task);
        } catch (DukeException e) {
            return TextUi.showError(e.getMessage());
        }
    }
}
