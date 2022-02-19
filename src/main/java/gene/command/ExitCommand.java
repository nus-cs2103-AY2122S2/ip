package gene.command;

import gene.component.*;

/**
 * The exit command class. Exits the program
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public class ExitCommand extends Command {

    /**
     * The execute methods contains the instruction(s) for when the command is
     * to be executed. Nothing happens other than the goodbye statement
     * being printed
     *
     * @param geneTasks the list of tasks
     * @param geneUi the Ui class task object
     * @param geneTaskStorage the tastorage class object
     * @param geneLocs the list of locations
     * @param geneLocationStorage the location storage class object
     */
    @Override
    public String execute(TaskList geneTasks, Ui geneUi, TaskStorage geneTaskStorage, LocationList geneLocs, LocationStorage geneLocationStorage) {
        return Ui.showLine() + "\n"
                + "Bye. Hope to see you again soon!"
                + "\n"
                + Ui.showLine();
    }

    /**
     * The method to distinguish this command from other commands
     *
     * @return must return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
