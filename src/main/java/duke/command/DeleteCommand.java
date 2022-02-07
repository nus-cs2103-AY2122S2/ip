package duke.command;

import java.util.ArrayList;
import java.util.Arrays;

import duke.task.Task;
import duke.utils.CortanaException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The type Delete command.
 */
public class DeleteCommand extends Command {
    private final int[] index;

    /**
     * Instantiates a new Delete command.
     *
     * @param index the index of task to be deleted, can delete more than 1 task at the same time
     */
    public DeleteCommand(int... index) {
        this.index = index;
    }

    /**
     * Delete a specific task in the task list.
     *
     * @param taskList the task list to operate on
     * @param ui the ui to operate on
     * @param storage the storage to operate on
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        try {
            ArrayList<Task> tasksDeleted = new ArrayList<>();
            Arrays.stream(index).forEach(index -> tasksDeleted.add(taskList.getTaskList().get(index)));

            taskList.getTaskList().removeAll(tasksDeleted);
            tasksDeleted.forEach(taskList.getTaskSet()::remove);

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.insert(0, ui.deletedTask(tasksDeleted.size()));
            tasksDeleted.forEach(task -> stringBuilder.append(task).append("\n"));

            storage.writeFile(taskList);
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new CortanaException("No such task!");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            DeleteCommand deleteCommand = (DeleteCommand) obj;
            return Arrays.equals(deleteCommand.index, this.index);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(index);
    }
}
