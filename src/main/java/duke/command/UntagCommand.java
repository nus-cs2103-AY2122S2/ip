package duke.command;

import duke.exceptions.DukeException;
import duke.operations.TaskList;

/**
 * Represents a subclass of Command.
 */
public class UntagCommand extends Command {
    /**
     * A constructor for <code>UntagCommand</code>.
     *
     * @param num the num of the arraylist to be untagged.
     */
    public UntagCommand(int num) {
        super(null, num, null);
    }

    /**
     * Untags the task in the arraylist.
     *
     * @param taskList the task to be untagged into the arraylist.
     * @return the task to be untagged.
     * @throws DukeException throws an exception related to Duke, IndexOutOfBounds.
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        try {
            return taskList.untagInList(num);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("you sussy baka, you can't untag a task that's outta bounds!");
        }
    }
}
