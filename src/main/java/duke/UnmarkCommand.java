package duke;
import java.io.IOException;

public class UnmarkCommand extends Command {

    private int taskToUnmark;

    /**
     * Constructs an instance of Unmark Command.
     *
     * @param taskToUnmark Integer position of task to delete in tasklist.
     */
    public UnmarkCommand(int taskToMark) {
        this.taskToUnmark = taskToUnmark;
    }

    /**
     * Executes the instance of Delete Command.
     *
     * @param tasks Contains the task list.
     * @param ui Deals with interaction with the user.
     * @param storage Deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        try {
            tasks.unmark(taskToUnmark);
            storage.writeToFile(tasks.getTaskArr());
            return ui.showUnmarkTask(tasks.getTask(taskToUnmark));
        } catch (IOException e) {
            return ui.showError("IOException");
        }
    }

    /**
     * Checks whether this command is the terminating command to Duke.
     *
     * @return False.
     */
    @Override
    public boolean isEnd() {
        return false;
    }
}
