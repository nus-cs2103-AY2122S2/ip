package main.java.duke;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmark(taskToUnmark);
        ui.showUnmarkTask(tasks.getTask(taskToUnmark));
        try {
            storage.writeToFile(tasks.getTaskArr());
        } catch (IOException e) {
            ui.showError("IOException");
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
