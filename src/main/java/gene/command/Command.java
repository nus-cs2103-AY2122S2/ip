package gene.command;

import gene.component.*;

/**
 * The abstract command class. From this class, the add, delete, exit, edit
 * and list commands are inherited.
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public abstract class Command {
    /**
     * abstract execute class that holds the instructions to be carried out
     * for each command todo: change all execute methods to have varargs
     *
     * @param geneTasks the list of tasks
     * @param geneUi the Ui class task object
     * @param geneTaskStorage the tastorage class object
     * @param geneLocs the list of locations
     * @param geneLocationStorage the location storage class object
     */
    public abstract String execute(TaskList geneTasks, Ui geneUi, TaskStorage geneTaskStorage, LocationList geneLocs, LocationStorage geneLocationStorage);

    /**
     * to distinguish whether a command is an exit command
     *
     * @return true if exit command, else false.
     */
    public abstract boolean isExit();
}
