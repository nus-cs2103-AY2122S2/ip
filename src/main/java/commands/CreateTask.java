package commands;

import java.util.List;

import mytasks.Deadline;
import mytasks.Event;
import mytasks.Task;
import mytasks.Todo;

/**
 * CreateTask class contain methods to create new tasks for the user to track.
 */
public class CreateTask {
    /**
     * Creates a task without restrictions.
     * @param description description of the task.
     * @param list list of tasks that are currently being tracked.
     * @param taskCount Number of tasks currently in the list.
     */
    public static String createTodo(String description, List<Task> list, int taskCount) {
        list.add(new Todo(description));

        return printCreateTask(list, taskCount);
    }

    /**
     * Creates a task that must be completed before a certain time.
     * @param description description of the task.
     * @param list list of tasks that are currently being tracked.
     * @param taskCount Number of tasks currently in the list.
     */
    public static String createDeadline(String description, List<Task> list, int taskCount) {
        String[] splitDescription = description.split("/by ", 2);

        list.add(new Deadline(splitDescription[0], splitDescription[1]));

        return printCreateTask(list, taskCount);
    }

    /**
     * Creates a task that will begin at a certain time.
     * @param description description of the task.
     * @param list list of tasks that are currently being tracked.
     * @param taskCount Number of tasks currently in the list.
     */
    public static String createEvent(String description, List<Task> list, int taskCount) {
        String[] splitDescription = description.split("/at ", 2);

        list.add(new Event(splitDescription[0], splitDescription[1]));

        return printCreateTask(list, taskCount);
    }

    /**
     * Inform user that the task has been created.
     * @param list description of the task.
     * @param taskCount list of tasks that are currently being tracked.
     */
    private static String printCreateTask(List<Task> list, int taskCount) {
        StringBuilder sb = new StringBuilder();

        sb.append(list.get(taskCount).toString() + "\n");
        sb.append("Got it. I've added this task:\n");

        return sb.toString();
    }
}
