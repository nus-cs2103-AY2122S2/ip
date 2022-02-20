package duke.command;

import duke.DukeException;
import duke.common.Messages;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * A class that mark task object in a task list.
 */
public class MarkTaskCommand extends Command {
    private static final boolean IS_EXIT = false;
    private final boolean isDone;
    private final int taskId;

    /**
     * Creates a MarkTaskCommand instance with a title.
     *
     * @param taskId The id of the targeted task.
     * @param isDone The mark action of the task.
     */
    public MarkTaskCommand(int taskId, boolean isDone) {
        super(IS_EXIT);
        this.taskId = taskId;
        this.isDone = isDone;
    }

    /**
     * Updates the mark status of a task object in the task list and
     * show the execution message on the GUI.
     *
     * @param tasks The current task list.
     * @param storage The current storage of Duke.
     * @return The string of the GUI message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            String task = tasks.markTask(taskId, isDone);
            String message;
            if (isDone) {
                message = Messages.MESSAGE_MARK;
            } else {
                message = Messages.MESSAGE_UNMARKED;
            }
            return TextUi.showExecutionMessage(message, task);
        } catch (DukeException e) {
            return TextUi.showError(e.getMessage());
        }
    }
}
