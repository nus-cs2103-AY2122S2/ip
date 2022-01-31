package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private String searchKey;

    /**
     * FindCommnd allows search of tasks that match the specified search key.
     * @param commandType request command.
     * @param searchKey searchKey user is looking for.
     */
    public FindCommand(CommandType commandType, String searchKey) {
        super(commandType);
        this.searchKey = searchKey.toLowerCase().trim();
    }

    @Override
    public boolean isActive() {
        return super.active;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> filteredTaskList = new ArrayList<>();
        boolean tasksMatchingSearchKey = false;

        // cycle through existing tasks & filter tasks
        for (Task task: taskList.getTasks()) {
            // check if task matches searchKey
            if (task.getDescription().contains(searchKey)) {
                filteredTaskList.add(task);
                tasksMatchingSearchKey = true;
            }
        }

        // if there are tasks that match the searchKey
        if (tasksMatchingSearchKey) {
            ui.showText("Here are the matching tasks in your list: ");
            for (Task task: filteredTaskList) {
                ui.showTask(task.toString());
            }
        } else {
            ui.showText("Looks like there aren't any matching tasks in your list!");
        }

    }

}
