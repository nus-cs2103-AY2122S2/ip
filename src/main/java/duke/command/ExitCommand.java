package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents command to exit the Duke program.
 *
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList task, Storage storage) {
        return "";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
