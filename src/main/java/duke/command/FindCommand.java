package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;

/**
 * Represents a command to find tasks.
 */
public class FindCommand extends Command {

    /**
     * Creates a FindCommand with event task.
     *
     * @param searchWord The word to search within the tasks.
     * @throws DukeException If searchWord is empty.
     */
    public FindCommand(String searchWord) throws DukeException {
        super(searchWord);
    }

    /**
     * Executes FindCommand to find all matching tasks in the TaskList.
     *
     * @param taskList TaskList for command to search for Task.
     * @return a String when command is called.
     */
    @Override
    public String callCommand(TaskList taskList) throws DukeException {
        return taskList.findTask(this.input);
    }
}
