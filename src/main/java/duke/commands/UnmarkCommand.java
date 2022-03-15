package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.IndexException;

/**
 * Represents a <code>UnmarkCommand</code> which is called to mark a task as Undone.
 */

public class UnmarkCommand implements Command {

    private int index;

    /**
     * Constructor for UnmarkCommand, to mark a task as Undone.
     * @param index
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        String response = "";
        if (index <= 0 || index > tasks.getSize()) {
            throw new IndexException();
        }
        Task set = tasks.setUndone(index - 1);
        response += "Ok, I've marked this task as not done yet.: \n" + set;
        return response;
    }
}
