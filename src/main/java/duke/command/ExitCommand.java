package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents command to exit the Duke program.
 *
 */
public class ExitCommand extends Command {
    private static final String REPLY = "Ok honey.\n";

    @Override
    public String execute(TaskList task, Storage storage) {
        return REPLY;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
