package duke.commands;

import duke.Storage;
import duke.TextUi;
import tasks.TaskList;

/**
 * Represents a command that allows users to find tasks
 * that exist in the task list
 * */
public class Find extends Command {
    private final String query;

    /**
     * Initialize a Find Command
     * @param query search query
     */
    public Find(String query) {
        this.query = query;
    }

    /**
     * Method that executes a command to find a task from the task list.
     * @param taskList a taskList containing all existing tasks
     * @param ui a ui object
     * @param storage a storage object that is able to read and write to storage file
     * @return message after a task has been found from the task list
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) {
        return TaskList.findTask(query);
    }

}
