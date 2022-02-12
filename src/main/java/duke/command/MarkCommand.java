package duke.command;

import duke.DukeException;
import duke.task.TaskList;

/**
 * A Command that marks the specified task when executed.
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Constructs a {@code MarkCommand} object.
     * @param index the index of the task to mark as done
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the specified index in the list of tasks as done.
     * @param tasks current list of tasks
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        if (index > tasks.size() || index <= 0) {
            throw new DukeException("Index out of bound");
        }
        tasks.set(index, tasks.get(index).mark());
        return "Nice! I've marked this task as done:\n  "
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
