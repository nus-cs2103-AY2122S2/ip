package command;

import task.TaskList;
import tsundere.Storage;

/**
 * Prints the string before exiting.
 */
public class ExitCommand extends Command {

    /**
     * Uses UI for printing exiting string.
     *
     * @param t TaskList for managing and adding tasks.
     * @param s Storage for saving to file.
     */
    public String execute(TaskList t, Storage s) {
        return ("Finally, you're leaving!\nIt's not like i will miss you or anything...");
    }

    /**
     * Determines if the class is ExitCommand.
     *
     * @return true always because it is an ExitCommand.
     */
    public boolean isExit() {
        return true;
    }
}
