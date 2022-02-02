package duke;
import java.util.ArrayList;

public class ListCommand extends Command {

    /**
     * Executes the instance of List Command.
     *
     * @param tasks Contains the task list.
     * @param ui Deals with interaction with the user.
     * @param storage Deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> t = tasks.getTaskArr();
        if (t.size() == 0) {
            return ui.showEmptyTask();
        }

        for (int i = 0; i < t.size(); i++) {
            return ui.showTask(i + 1, t.get(i));
        }
        return null;
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
