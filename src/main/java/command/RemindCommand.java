package command;

import task.TaskList;
import tsundere.Storage;

/**
 * Lists all upcoming deadlines from tasklist.
 */
public class RemindCommand extends Command {

    /**
     * Lists all upcoming deadlines from tasklist.
     *
     * @param t TaskList for managing and adding tasks.
     * @param s Storage for saving to file.
     */
    public String execute(TaskList t, Storage s) {
        return (t.remindTasks());
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
