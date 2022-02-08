package duke.commands;

import duke.tasks.TaskList;
import duke.storage.Storage;

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
