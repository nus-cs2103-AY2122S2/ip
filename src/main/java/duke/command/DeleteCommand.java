package duke.command;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * DeleteCommand is a command that handles delete instructions.
 *
 * @author yl-ang
 */
public class DeleteCommand extends Command {

    private boolean isExit = false;
    private int taskNum;

    /**
     * Constructs DeleteCommand object with the specified index.
     *
     * @param index The specified index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.taskNum = index;
    };

    /**
     * Executes DeleteCommand with the objects supplied.
     *
     * @param taskLst TaskLst object containing the current tasks.
     * @param ui Ui object containing the user interface messages and scanner object.
     * @param storage Storage object containing the methods to load and save.
     * @return Formatted response from executing command.
     */
    @Override
    public String execute(TaskList taskLst, Ui ui, Storage storage) {
        Task task = taskLst.deleteTask(this.taskNum);
        storage.save(taskLst.getTaskLst());
        String response = ui.deletedTasksResponse(task, taskLst.getTaskLst());
        assert response != null : "Delete response should not be null";
        return response;
    }

    /**
     * Checks and returns the boolean whether if command is exit.
     *
     * @return Boolean if the command is exit.
     */
    @Override
    public boolean isExit() {
        return isExit;
    }
}

