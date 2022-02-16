package duke.command;

import duke.exceptions.DukeException;
import duke.operations.TaskList;

/**
 * Represents a subclass of Command.
 */
public class TagCommand extends Command {

    /**
     * A constructor for <code>TagCommand</code>.
     *
     * @param num the num of the arraylist to be tagged.
     * @param keyword the tag to be added.
     */
    public TagCommand(int num, String keyword) {
        super(null, num, keyword);
    }

    /**
     * Tags the task in the arraylist.
     *
     * @param taskList the task to be tagged into the arraylist.
     * @return the task to be tagged.
     * @throws DukeException throws an exception related to Duke, IndexOutOfBounds.
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        try {
            return taskList.tagInList(num, keyword);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("you sussy baka, you can't tag a task that's outta bounds!");
        }
    }
}
