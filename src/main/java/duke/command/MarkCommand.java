package duke.command;

import java.util.Objects;

import duke.task.Task;
import duke.utils.CortanaException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

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

    /**
     * Mark a specific task as done.
     *
     * @param taskList the task list to operate on
     * @param ui the ui to operate on
     * @param storage the storage to operate on
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        try {
            Task task = taskList.getTaskList().get(index);
            task.markAsDone();
            storage.writeFile(taskList);
            return ui.marked(task);
        } catch (Exception e) {
            assert index <= 0 : "Index is greater than 0";
            throw new CortanaException("No such task!");
        }
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
