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

    private static String getTaskList(List<Task> tasks) {
        StringBuilder result = new StringBuilder("List of tasks: \n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append("     " + (i + 1) + ". " + tasks.get(i).toString());
            if (i != tasks.size() - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }
}
