package duke.command;

import java.util.Objects;

import duke.task.Task;
import duke.utils.CortanaException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The type Delete command.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Instantiates a new Delete command.
     *
     * @param index the index of task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Execute delete task operation.
     *
     * @param taskList the task list to operate on
     * @param ui the ui to operate on
     * @param storage the storage to operate on
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        try {
            Task taskDeleted = taskList.getTaskList().get(index);
            taskList.getTaskList().remove(index);
            taskList.getTaskSet().remove(taskDeleted);
            storage.writeFile(taskList);
            ui.deletedTask(taskList, taskDeleted);
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
            DeleteCommand deleteCommand = (DeleteCommand) obj;
            return deleteCommand.index == this.index;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
