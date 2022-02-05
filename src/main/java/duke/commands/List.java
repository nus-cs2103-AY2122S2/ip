package duke.commands;

import duke.Storage;
import duke.TextUi;
import tasks.TaskList;

/**
 * Represents a command that allows users to list tasks in the task list
 */
public class List extends Command {
    /**
     * Method that executes a command to list tasks from the task list.
     * @param taskList a taskList containing all existing tasks
     * @param ui a ui object
     * @param storage a storage object that is able to read and write to storage file
     * @return list of tasks stored in the task list
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) {
        return TaskList.listTasks();
    }
}
