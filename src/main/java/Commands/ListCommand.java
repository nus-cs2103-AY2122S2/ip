package Commands;

import Tasks.Task;
import Tasks.TaskList;
import util.Storage;
import util.Ui;

public class ListCommand extends DukeCommand{

    public ListCommand(String description) {
        super(description);
    }

    /**
     * Executes when the user inputs the keyword "list"
     * @param tasks The current list of tasks
     * @param ui The object that deals with user interaction
     * @param storage The object that deals with the management of the database
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}
