package duke.command;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * UnmarkCommand is a command that handles unmark commands.
 *
 * @author yl-ang
 */
public class UnmarkCommand extends Command {

    private boolean isExit = false;
    private int taskNum;

    /**
     * Constructs UnmarkCommand object with the specified task number.
     *
     * @param taskNum Task number specified by the user.
     */
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes UnmarkCommand with the objects supplied.
     *
     * @param taskLst TaskLst object containing the current tasks.
     * @param ui Ui object containing the user interface messages and scanner object.
     * @param storage Storage object containing the methods to load and save.
     * @return Formatted response from executing command.
     */
    @Override
    public String execute(TaskList taskLst, Ui ui, Storage storage) {
        Task selectedTask = taskLst.markOrUnmarked("unmark", this.taskNum);
        storage.save(taskLst.getTaskLst());
        String response = ui.unmarkResponse(selectedTask);
        assert response != null : "Unmarked response should not be null";
        return response;
    }

    /**
     * Checks and returns the boolean whether if command is exit.
     *
     * @return Boolean if command is exit.
     */
    @Override
    public boolean isExit() {
        return this.isExit;
    }
}
