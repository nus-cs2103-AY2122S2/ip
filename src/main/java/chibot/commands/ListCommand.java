package chibot.commands;

import chibot.storage.Storage;
import chibot.tasklist.TaskList;

/**
 * Command for listing all task in the Tasklist.
 */
public class ListCommand extends Command {

    /**
     * Returns all tasks in String format.
     *
     * @param tl The TaskList from each task will be added/deleted.
     * @param sge The Storage which stores/removes tasks from the hard-disk.
     * @return A string of all tasks, each starting on new line.
     */
    @Override
    public String execute(TaskList tl, Storage sge) {
        return tl.getTasksMsg();
    }
}
