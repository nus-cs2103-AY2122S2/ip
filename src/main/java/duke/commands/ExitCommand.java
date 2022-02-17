package duke.commands;

import duke.Message;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Outputs exit message.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        storage.saveTaskList(tasks);
        return Message.MESSAGE_EXIT;
    }
}
