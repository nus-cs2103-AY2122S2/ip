package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

/**
 * Command to list and print the current task list.
 */
public class ListCommand extends Command {

    /**
     * Constructor to list all tasks in teh task list.
     */
    public ListCommand(){}

    /** {@inheritDoc} */
    @Override
    public String exec(TaskList taskList, Storage storage) {
        return taskList.list();
    }
}
