package duke.command;

import java.util.Objects;

import duke.task.Task;
import duke.utils.CortanaException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The type Add command.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Instantiates a new Add command.
     *
     * @param task the task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Execute the add task operation.
     *
     * @param taskList the task list to operate on
     * @param ui the ui to operate on
     * @param storage the storage to operate on
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        if (taskList.getTaskSet().contains(task)) {
            throw new CortanaException("Task already exists!");
        } else {
            taskList.getTaskSet().add(task);
            taskList.getTaskList().add(task);
            try {
                storage.writeFile(taskList);
            } catch (Exception e) {
                throw new CortanaException(e.getMessage());
            }
            ui.addedTask(taskList, task);
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
            AddCommand addCommand = (AddCommand) obj;
            return addCommand.task.equals(this.task);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(task);
    }
}
