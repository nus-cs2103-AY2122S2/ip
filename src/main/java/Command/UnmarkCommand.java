package Command;

import DukeUtils.CortanaException;
import DukeUtils.Storage;
import DukeUtils.TaskList;
import DukeUtils.Ui;
import Task.Task;

import java.util.Objects;

/**
 * The type Unmark command.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Instantiates a new Unmark command.
     *
     * @param index the index of task to be unmarked
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        try {
            Task task = taskList.tasksArrayList.get(index);
            task.markAsUndone();
            storage.writeFile(taskList);
            ui.unmarked(task);
        } catch (Exception e) {
            throw new CortanaException("No such task!");
        }
    }

    /**
     * The program is not yet exited.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            UnmarkCommand unmarkCommand = (UnmarkCommand) obj;
            return unmarkCommand.index == this.index;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
