package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Lists out the tasks in the list to user.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.taskListToString();
    }
}
