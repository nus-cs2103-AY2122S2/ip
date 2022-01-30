package instruction;

import task.Task;
import task.TaskManager;
import ui.Ui;

import java.util.*;

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

        ui.printMessage(ListTasks.getTaskList(tasks));
    }

    /**
     * Obtains the list of tasks, and convert them into a single string.
     *
     * @param tasks The list of tasks to be converted.
     * @return The converted string.
     */
    private static String getTaskList(List<Task> tasks) {

        StringBuilder result = new StringBuilder("List of tasks:\n");

        for (int i = 0; i < tasks.size(); i++) {
            result.append("     ").append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }

        result.append(">> Now you have ").append(tasks.size()).append(" tasks in the list.");
        return result.toString();
    }
}
