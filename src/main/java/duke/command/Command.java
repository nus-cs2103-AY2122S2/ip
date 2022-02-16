package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * An abstract class that adds task object into a task list.
 */
public abstract class Command {
    private final boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Adds the task object to into the task list and show the execution message on the GUI.
     *
     * @param tasks The current task list.
     * @param storage The current storage of Duke.
     * @return The string of the GUI message.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.isExit;
    }
}
