package command;

import storage.Storage;
import task.TaskList;

public class ListCommand extends Command {
    /**
     * Output entire TaskList.
     *
     * @param storage Storage for rewriting TaskList.
     * @param taskList TaskList that stores Tasks.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        return "Here are the tasks in your list:\n" + taskList;
    }
}
