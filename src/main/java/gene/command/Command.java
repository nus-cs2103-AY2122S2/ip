package gene.command;

import gene.component.Storage;
import gene.component.TaskList;
import gene.component.Ui;

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
     * for each command
     *
     * @param tasks the list of tasks
     * @param userInt the Ui class object
     * @param storage the storage class object
     */
    public abstract void execute(TaskList tasks, Ui userInt, Storage storage);

    /**
     * to distinguish whether a command is an exit command
     *
     * @return true if exit command, else false.
     */
    public abstract boolean isExit();
}
