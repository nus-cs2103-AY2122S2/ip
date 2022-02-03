package duke.command;

import duke.Ui;
import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to mark a task as uncompleted in the Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class UndoCommand extends Command {
    int taskId;

    /**
     * Constructor to create an Undo Command.
     *
     * @param taskId Zero index task id to mark a task a complete from the task list.
     */
    public UndoCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Execute the command to mark the task as not done.
     *
     * @param taskList The list of task in the Duke application.
     * @param storage  Storage of task in local persistent disk.
     * @throws DukeException Invalid task id (Either already completed or out or range).
     * @exception IOException
     * @see IOException
     */
    public void execute(TaskList taskList, Storage storage) throws IOException, DukeException {
        if (taskId >= taskList.getTotalTasks() || taskId < 0) {
            throw new DukeException(Ui.MSG_INVALIDTASKID);
        } else if (!taskList.isDone(taskId)) {
            throw new DukeException(Ui.MSG_TASKNOTCOMPLETE);
        } else {
            taskList.uncompleteTask(taskId);
            Ui.print(Ui.updateTaskMsg(taskList.getTask(taskId).toString()));
            storage.writeToFile(taskList);
        }
    }
}
