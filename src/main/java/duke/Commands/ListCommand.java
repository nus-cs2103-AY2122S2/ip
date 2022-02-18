package duke.Commands;

import duke.Tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * ListCommand is a subclass of DukeCommand that is created when the user inputs "list"
 */
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
        if (tasks.getSize() == 0) {
            return "The list is empty! Please add a task first";
        }
        return ui.showTaskList(tasks);
    }
}
