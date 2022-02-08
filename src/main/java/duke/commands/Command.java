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
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
