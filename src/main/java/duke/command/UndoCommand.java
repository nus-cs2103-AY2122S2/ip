package duke.command;

import java.io.IOException;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;

/**
 * Represents a command to mark a task as uncompleted in the Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class UndoCommand extends Command {
    private final int taskId;

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
    public String execute(TaskList taskList, Storage storage) throws IOException, DukeException {
        if (taskId >= taskList.getTotalTasks() || taskId < 0) {
            throw new DukeException(Ui.MSG_INVALIDTASKID);
        } else if (!taskList.isDone(taskId)) {
            throw new DukeException(Ui.MSG_TASKNOTCOMPLETE);
        } else {
            taskList.uncompleteTask(taskId);
            storage.writeToFile(taskList);
            return Ui.updateTaskMsg(taskList.getTask(taskId).toString());
        }
    }

    /**
     * Generate the usage guide for this command.
     *
     * @return Returns the formatted String value for printing for the usage guide.
     */
    public static String usage() {
        return "To mark a task as incomplete, use the undo command followed by the task number.\n"
                + "(Hint: Use the list command to find the task number)\n"
                + "  Usage: undo <Task Id> | i.e. undo 3\n\n";
    }
}
