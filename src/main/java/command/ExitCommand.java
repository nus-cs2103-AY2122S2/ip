package command;

import task.TaskList;
import tsundere.Storage;
import tsundere.Ui;

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
        u.wrapText("Finally, you're leaving!\nIt's not like i will miss you or anything...");
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
