package main.java.duke;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(taskToMark);
        ui.showMarkTask(tasks.getTask(taskToMark));
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
