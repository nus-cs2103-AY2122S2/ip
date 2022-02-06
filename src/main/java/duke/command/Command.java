package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * An abstract class Command that is extended to create the respective subclasses.
 */
public abstract class Command {
    /**
     * Executes the command according to its subclass.
     *
     * @param tasks   the tasks in `TaskList`
     * @param ui      the UI that the user interacts with
     * @param storage the storage that is used to read/write to the local file
     * @return a string to indicate what has been executed
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);


    /**
     * Returns `true` if the `Command` is `ExitCommand`.
     *
     * @return `true` if the `Command` is `ExitCommand`
     */
    public boolean isExit() {
        return false;
    };
}
