package duke.command;

import java.util.ArrayList;
import java.util.Arrays;

import duke.task.Task;
import duke.utils.CortanaException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The type Mark command.
 */
public class MarkCommand extends Command {
    private final int[] index;

    /**
     * Instantiates a new Mark command.
     *
     * @param index the index of task to be marked, can mark more than 1 task at the same time
     */
    public MarkCommand(int... index) {
        this.index = index;
    }

    /**
     * Mark a specific task as done.
     * If the given index is not within 1 and the total number of tasks, exception will be thrown.
     *
     * @param taskList the task list to operate on
     * @param ui the ui to operate on
     * @param storage the storage to operate on
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        try {
            ArrayList<Task> tasksMarked = new ArrayList<>();
            Arrays.stream(index).forEach(index -> taskList.getTaskList().get(index).markAsDone());
            Arrays.stream(index).forEach(index -> tasksMarked.add(taskList.getTaskList().get(index)));

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.insert(0, ui.marked(tasksMarked.size()));
            tasksMarked.forEach(task -> stringBuilder.append(task).append("\n"));

            storage.writeFile(taskList);
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new CortanaException("No such task!");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            MarkCommand markCommand = (MarkCommand) obj;
            return Arrays.equals(markCommand.index, this.index);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(index);
    }
}
