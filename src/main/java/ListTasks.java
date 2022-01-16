import java.util.*;

/**
 * Represents the instruction "list".
 */
final class ListTasks extends Instruction {

    /**
     * Constructor of the instruction, adds the description.
     */
    protected ListTasks() {
        super.setDescription("list");
    }

    /**
     * Performs a search on the list of tasks, and return them as formatted strings.
     *
     * @return A list of tasks that are not done yet.
     */
    @Override
    protected String act() {
        List<Task> tasks = TaskManager.listOfTasks();

        return ListTasks.getTaskList(tasks);
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
            result.append("     " + (i + 1) + ". " + tasks.get(i).toString() + "\n");
        }

        result.append(">> Now you have " + tasks.size() + " tasks in the list.");
        return result.toString();
    }
}
