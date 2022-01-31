package funbox.command;

import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;

public class FindCommand extends Command {
    String description;
    public FindCommand(String description) {
        super(false);
        this.description = description;
    }

    /**
     * Execute the command to find a task on the list.
     *
     * @param taskList List of tasks.
     * @param ui Interface which interact with users.
     * @param storage Stores user tasks locally.
     * @return Returns a string to be displayed to the user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.findTasks(this.description, ui, taskList);
    }
}
