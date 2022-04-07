package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;

/**
 * Represents a command that list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Creates a ListCommand.
     *
     * @param description .
     */
    public ListCommand(String description) {
        super(description);
    }

    /**
     * Execute ListCommand to list out all tasks of a TaskList.
     *
     * @param taskList The TaskList for command act on.
     * @throws DukeException If taskList is empty.
     * @return a String when command is called.
     */
    @Override
    public String callCommand(TaskList taskList) throws DukeException {
        return taskList.printOutTaskList();
    }
}
