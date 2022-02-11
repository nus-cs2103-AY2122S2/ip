package duke.commands;

import duke.admin.Storage;
import duke.admin.TaskList;
import duke.admin.Ui;

/**
 * ListCommand is a Command that triggers the program to print out the task list
 * with proper indexing according to chronological order of when the task was
 * added into the task list.
 */
public class ListCommand extends Command {

    /**
     * Lists out the tasks stored in the tasks list.
     * @param tasks   task list local to user
     * @param storage storage instance local to user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return Ui.listTasks(tasks);
    }

    /**
     * Checks if this is an exit command, and only returns true for an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
