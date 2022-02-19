package duke;
import java.util.ArrayList;

/**
 * Class that represents listing all tasks in the form of a command.
 */
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
        String s = "Here are the tasks in your list:\n";
        for (int i = 0; i < t.size(); i++) {
            s += ui.showTask(i + 1, t.get(i));
            if (i == t.size() - 1) {
                break;
            }
            s += '\n';
        }
        return s;
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
