package duke.command;

import java.util.ArrayList;
import java.util.Arrays;

import duke.task.Task;
import duke.utils.CortanaException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The type Unmark command.
 */
public class UnmarkCommand extends Command {
    private final int[] index;

    /**
     * Instantiates a new Unmark command.
     *
     * @param index the index of task to be unmarked, can unmark more than 1 task at the same time
     */
    public UnmarkCommand(int... index) {
        this.index = index;
    }

    /**
     * Mark a specific task as undone.
     *
     * @param taskList the task list to operate on
     * @param ui the ui to operate on
     * @param storage the storage to operate on
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        try {
            ArrayList<Task> tasksUnmarked = new ArrayList<>();
            Arrays.stream(index).forEach(index -> taskList.getTaskList().get(index).markAsUndone());
            Arrays.stream(index).forEach(index -> tasksUnmarked.add(taskList.getTaskList().get(index)));


            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.insert(0, ui.unmarked(tasksUnmarked.size()));
            tasksUnmarked.forEach(task -> stringBuilder.append(task).append("\n"));

            storage.writeFile(taskList);
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new CortanaException("No such task!");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            UnmarkCommand unmarkCommand = (UnmarkCommand) obj;
            return Arrays.equals(unmarkCommand.index, this.index);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(index);
    }
}
