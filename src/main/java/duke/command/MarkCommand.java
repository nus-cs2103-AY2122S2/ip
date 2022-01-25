package duke.command;

import duke.task.Task;

import duke.utils.CortanaException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.Objects;

/**
 * The type Mark command.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Instantiates a new Mark command.
     *
     * @param index the index of task to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        try {
            Task task = taskList.tasksArrayList.get(index);
            task.markAsDone();
            storage.writeFile(taskList);
            ui.marked(task);
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
            MarkCommand markCommand = (MarkCommand) obj;
            return markCommand.index == this.index;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
