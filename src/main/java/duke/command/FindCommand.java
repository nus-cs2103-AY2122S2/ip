package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.gui.Ui;

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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> originalTaskList = taskList.getTasks();
        ArrayList<Task> filteredTaskList = new ArrayList<>();
        boolean tasksMatchingSearchKey = false;

        // cycle through existing tasks & filter tasks
        for (Task task: originalTaskList) {
            // check if task matches searchKey
            if (task.getDescription().contains(searchKey)) {
                filteredTaskList.add(task);
                tasksMatchingSearchKey = true;
            }
        }

        // if there are tasks that match the searchKey
        if (tasksMatchingSearchKey) {
            ui.addText("Here are the matching tasks in your list: ");
            for (Task task: filteredTaskList) {
                ui.listTask(originalTaskList.indexOf(task), task);
            }
        } else {
            ui.addText("Looks like there aren't any matching tasks in your list!");
        }

        return ui.generateDukeResponse();
    }

}
