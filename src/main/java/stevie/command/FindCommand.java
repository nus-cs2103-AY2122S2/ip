package stevie.command;

import stevie.StevieUi;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;

/**
 * FindCommand is executed to retrieve all tasks in task list that contains
 * a query string.
 */
public class FindCommand extends Command {
    /**
     * Immutable query string to as argument to search in task list.
     */
    private final String query;

    /**
     * Constructor for an FindCommand
     *
     * @param queryStr string that is used to query for tasks
     */
    public FindCommand(String queryStr) {
        query = queryStr;
    }

    /**
     * Executes the find command and lets UI output the response string.
     *
     * @param tasks   task list to find from
     * @param storage task list data handler
     * @param ui      user interface to print response string
     * @return false to indicate that command does not terminate Stevie
     */
    @Override
    public boolean execute(TaskList tasks, TaskDataHandler storage, StevieUi ui) {
        ui.outputMessage(tasks.find(query));
        return false;
    }
}
