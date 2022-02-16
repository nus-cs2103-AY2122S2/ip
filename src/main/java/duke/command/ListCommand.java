package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * A class that list all task objects in a task list.
 */
public class ListCommand extends Command {
    private static final boolean IS_EXIT = false;

    /**
     * Creates a ListCommand instance.
     */
    public ListCommand() {
        super(IS_EXIT);
    }

    /**
     * Lists all task objects and show the tasks on the GUI.
     *
     * @param tasks The current task list.
     * @param storage The current storage of Duke.
     * @return All the tasks objects formatted for the GUI message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            return TextUi.showTasks(tasks);
        } catch (DukeException e) {
            return TextUi.showError(e.getMessage());
        }
    }
}
