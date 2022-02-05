package duke.instruction;

import java.util.List;

import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents the instruction "list".
 */
final class ListTasks extends Instruction {

    /**
     * Constructor of the instruction, adds the description.
     */
    ListTasks(TaskManager tasks) {
        super("list", tasks);
    }

    /**
     * Performs a search on the list of tasks, and return them as formatted strings.
     *
     * @param ui The UI to be used.
     */
    @Override
    public void act(Ui ui) {
        List<Task> tasks = this.tasks.listOfTasks();

        ui.printMessage(TaskManager.listToString(tasks));
    }

}
