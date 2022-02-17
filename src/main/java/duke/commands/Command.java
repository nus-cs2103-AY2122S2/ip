package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents a generic command.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks List of tasks.
     * @param storage Local storage file.
     */
    public abstract String execute(TaskList tasks, Storage storage);
}
