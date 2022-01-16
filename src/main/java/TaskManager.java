import java.util.ArrayList;

/**
 * An object that stores a list of tasks, performs actions on tasks.
 */
public class TaskManager {

    // A list of tasks stored.
    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Marks the status of the task as done.
     *
     * @param task The task to be operated on.
     */
    protected static void markAsDone(Task task) {
        task.markAsDone();
    }

    /**
     * Marks the status of the task as not done.
     *
     * @param task The task to be operated on.
     */
    protected static void markAsNotDone(Task task) {
        task.markAsNotDone();
    }

    /**
     * Lists all the tasks.
     *
     * @return The list of all the tasks.
     */
    protected static ArrayList<Task> listOfTasks() {

        return TaskManager.tasks;
    }

    /**
     * Adds a task into the list.
     *
     * @param task
     * @return The message after the task is added.
     */
    protected static String addTask(Task task) {
        TaskManager.tasks.add(task);
        return "added: " + task.toString();
    }

    /**
     * Gets the task with the specified index.
     * @param index The index of task to be retrieved (1-indexed).
     * @return The retrieved task.
     */
    protected static Task getTaskIndex(int index) {
        return TaskManager.tasks.get(index - 1);
    }

}
