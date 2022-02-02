package command;

import task.TaskList;

import tsundere.Ui;
import tsundere.Storage;

/**
 * List all tasks from tasklist and uses UI to print it
 */
public class ListCommand extends Command {
    /**
     * List all tasks from tasklist and uses UI to print it
     *
     * @param t TaskList for managing and adding tasks
     * @param u UI for displaying text
     * @param s Storage for saving to file
     */
    public void execute(TaskList t, Ui u, Storage s) {
        u.printWrapper(t.listTasks());
    }

    /**
     * Determine if the class is ExitCommand.
     *
     * @return False always because it is not an ExitCommand
     */
    public boolean isExit() {
        return false;
    }
}
