package duke.command;

import duke.DukeException;
import duke.task.TaskList;

/**
 * A Command that unmarks the specified task when executed.
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Constructs an {@code UnmarkCommand} object.
     * @param index the index of the task to unmark
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Unmarks the task at the specified index in the list of tasks.
     * @param tasks current list of tasks
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        if (index > tasks.size() || index <= 0) {
            throw new DukeException("Index out of bound");
        }
        tasks.set(index, tasks.get(index).unmark());
        return "OK, I've marked this task as not done yet:\n  "
                + tasks.get(index);
    }

    /**
     * Indicates that the program should not be exited.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
