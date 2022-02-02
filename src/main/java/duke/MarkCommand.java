package duke;
import java.io.IOException;

public class MarkCommand extends Command {

    private int taskToMark;

    public MarkCommand(int taskToMark) {
        this.taskToMark = taskToMark;
    }

    /**
     * Executes the instance of Mark Command.
     *
     * @param tasks Contains the task list.
     * @param ui Deals with interaction with the user.
     * @param storage Deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        try {
            tasks.mark(taskToMark);
            storage.writeToFile(tasks.getTaskArr());
            return ui.showMarkTask(tasks.getTask(taskToMark));
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
