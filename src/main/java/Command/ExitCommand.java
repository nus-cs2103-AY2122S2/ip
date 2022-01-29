package Command;

import Task.TaskList;

import Main.Ui;
import Main.Storage;

/**
 * Print the string before exiting
 */
public class ExitCommand extends Command {

    /**
     * Use UI for printing exiting string
     *
     * @param t TaskList for managing and adding tasks
     * @param u UI for displaying text
     * @param s Storage for saving to file
     */
    public void execute(TaskList t, Ui u, Storage s) {
        u.printWrapper("Finally, you're leaving!\nIt's not like i will miss you or anything...");
    }

    /**
     * Determine if the class is ExitCommand.
     *
     * @return True always because it is an ExitCommand
     */
    public boolean isExit() {
        return true;
    }
}
