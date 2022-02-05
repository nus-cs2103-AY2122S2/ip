package command;

import task.TaskList;
import tsundere.Storage;

/**
 * Lists all tasks from tasklist and uses UI to print it.
 */
public class ListCommand extends Command {
    /**
     * Lists all tasks from tasklist and uses UI to print it.
     *
     * @param t TaskList for managing and adding tasks
     * @param s Storage for saving to file
     */
    public String execute(TaskList t, Storage s) {
        return (t.listTasks());
    }

    /**
     * Determines if the class is ExitCommand.
     *
     * @return False always because it is not an ExitCommand.
     */
    public boolean isExit() {
        return false;
    }
}
