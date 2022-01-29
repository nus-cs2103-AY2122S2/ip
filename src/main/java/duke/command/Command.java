package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    /**
     * Executes the command according to its subclass.
     *
     * @param tasks   the tasks in `TaskList`
     * @param ui      the UI that the user interacts with
     * @param storage the storage that is used to read/write to the local file
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns `true` if the `Command` is `ExitCommand`.
     *
     * @return `true` if the `Command` is `ExitCommand`
     */
    public abstract boolean isExit();
}