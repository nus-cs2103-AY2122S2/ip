package duke.command;

import duke.DukeException;
import duke.task.TaskList;

/**
 * A Command that deletes the specified task when executed.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a {@code DeleteCommand} object.
     * @param index the index of the task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task at the specified index in the list of tasks.
     * @param tasks current list of tasks
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        if (index > tasks.size() || index <= 0) {
            throw new DukeException("Index out of bound");
        }
        String response = "Noted. I've removed this task:\n  " + tasks.get(index);
        tasks.remove(index);
        return response;
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
