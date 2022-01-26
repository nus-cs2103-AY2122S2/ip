package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Represents a generic command.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks List of tasks.
     * @param ui Ui used to show result messages.
     * @param storage Local storage file.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks for it is an exit command.
     *
     * @return True for exit command, or false otherwise.
     */
    public abstract boolean isExit();
}
