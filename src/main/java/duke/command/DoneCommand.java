package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;
import duke.Ui;

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
    public void execute(TaskList taskList, Storage storage) throws IOException, DukeException {
        if (taskId >= taskList.getTotalTasks() || taskId < 0) {
            throw new DukeException(Ui.MSG_INVALIDTASKID);
        } else if (taskList.isDone(taskId)) {
            throw new DukeException(Ui.MSG_TASKALREADYDONE);
        } else {
            taskList.completeTask(taskId);
            Ui.print(Ui.completeTaskMsg(taskList.getTask(taskId).toString()));
            storage.writeToFile(taskList);
        }
    }

    /**
     * Generate the usage guide for this command.
     *
     * @return Returns the formatted String value for printing for the usage guide.
     */
    public static String usage() {
        return """
                To mark a task as complete, use the done command followed by the task number.
                (Hint: Use the list command to find the task number)
                  Usage: done <Task Id> | i.e. done 3

                """;
    }
}
