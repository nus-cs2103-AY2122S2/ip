package duke.command;

import duke.Ui;
import duke.Storage;
import duke.task.TaskList;

public class ListCommand extends Command {

    /**
     * Constructs a {@code ListCommand} object.
     */
    public ListCommand() {}

    /**
     * Asks the UI to display the list of tasks.
     * @param tasks current list of tasks
     * @param ui the UI used
     * @param storage the storage used
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            return "There are no tasks in your list~";
        } else {
            String res = "Here are the tasks in your list:";
            for (int i = 0; i < tasks.size(); i++) {
                res += "\n" + tasks.get(i);
            }
            return res;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
