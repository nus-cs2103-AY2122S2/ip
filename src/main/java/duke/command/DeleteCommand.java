package duke.command;

import java.io.IOException;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to delete task from the Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class DeleteCommand extends Command {
    private int taskId;

    /**
     * Constructor to create a Delete Command.
     *
     * @param taskId Zero index task id to be removed from the task list.
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Execute the command to delete the given task.
     *
     * @param taskList The list of task in the Duke application.
     * @param storage  Storage of task in local persistent disk.
     * @throws DukeException Custom error message by the Duke application.
     * @exception IOException
     * @see IOException
     */
    public String execute(TaskList taskList, Storage storage) throws IOException, DukeException {
        if (taskId > taskList.getTotalTasks() || taskId < 0) {
            throw new DukeException(Ui.MSG_INVALIDTASKID);
        } else {
            Task task = taskList.getTask(taskId);
            int totalTask = taskList.deleteTask(taskId);
            storage.writeToFile(taskList);
            return Ui.deleteTaskMsg(task.toString(), totalTask + 1);
        }
    }

    /**
     * Generate the usage guide for this command.
     *
     * @return Returns the formatted String value for printing for the usage guide.
     */
    public static String usage() {
        return "To delete a task, use the delete command followed by the task number.\n"
                + "(Hint: Use the list command to find the task number)\n"
                + "  Usage: delete <Task Id> | i.e. delete task 3\n\n";
    }
}
