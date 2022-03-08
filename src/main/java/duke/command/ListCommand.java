package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * List command for Duke to list out all current tasks.
 */
public class ListCommand extends Command {
    public static final String LIST_COMMAND = "list";

    private static final String LIST_STATEMENT = "Here are the tasks in your list: \n";

    /**
     * List Command constructor.
     *
     * <p>Calls superclass Command constructor.</p>
     */
    public ListCommand() {
        super(LIST_COMMAND);
    }

    /**
     * Execution behavior of the list command.
     *
     * <p>Prints out the current tasks in the list using the Duke UI.</p>
     *
     * @param input User input
     * @param taskList User tasklist.
     * @param storage Storage to store the updated tasklist.
     * @return The task list description.
     * @throws DukeException No exeception.
     */
    @Override
    public String execute(String input, TaskList taskList, Storage storage) throws DukeException {
        return LIST_STATEMENT + taskList.getTaskListStr();
    }
}
