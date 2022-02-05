package duke.commands;

import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * The CommandUnmark class contains the
 * behaviour of an Empty Command.
 *
 * @author  Melvin Chan Zijun
 */
public class CommandEmpty extends Command {
    /**
     * Overrides method in parent class.
     * Does not do anything. Used to replace null checks.
     *
     * @param tasks - not used
     * @param ui - not used
     * @param storage - not used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {}
}
