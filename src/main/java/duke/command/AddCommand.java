package duke.command;

import duke.task.Task;

import duke.utils.CortanaException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.Objects;

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

    public void execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        if (taskList.taskSet.contains(task)) {
            throw new CortanaException("Task already exists!");
        } else {
            taskList.taskSet.add(task);
            taskList.tasksArrayList.add(task);
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
