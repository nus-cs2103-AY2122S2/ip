package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to mark a task as complete in the Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class DoneCommand extends Command {

    int taskId;

    /**
     * Constructor to create a Done Command.
     *
     * @param taskId Zero index task id to mark a task a complete from the task list.
     */
    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Execute the command to mark the task as done.
     *
     * @param taskList The list of task in the Duke application.
     * @param storage  Storage of task in local persistent disk.
     * @throws DukeException Invalid task id (Either already completed or out or range).
     * @exception IOException
     * @see IOException
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {

        if (taskList.isDone(taskId)) {
            throw new DukeException(ui.MSG_TASKALREADYDONE);
        } else if (taskId > taskList.getTotalTasks() || taskId < 0) {
            throw new DukeException(ui.MSG_INVALIDTASKID);
        } else {
            taskList.completeTask(taskId);
            ui.print(Ui.completeTaskMsg(taskList.getTask(taskId).toString()));
            storage.writeToFile(taskList);
        }
    }
}
