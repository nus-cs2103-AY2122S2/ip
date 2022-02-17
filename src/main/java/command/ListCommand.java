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
        if (taskList.size() == 0) {
            return "TAKE THAT!\nThere are no tasks in your list right now.";
        }
        return "TAKE THAT!\nHere are the tasks in your list:\n" + taskList;
    }
}
