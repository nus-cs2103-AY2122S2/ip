package tasks;

import tasks.Task;

import java.util.ArrayList;

/**
 * Class to represent a list of tasks.
 */
public class TaskList {

    /**
     * Static class member for task list.
     */
    public static ArrayList<Task> dukeList = new ArrayList<>();

    /**
     * Method to add a task to the list.
     * @param task Task to be added.
     */
    public static void add(Task task) {
        dukeList.add(task);
    }

    /**
     * Method to delete a task from the list.
     * @param taskIndex Task index for the task to be deleted.
     */
    public static void delete(int taskIndex) {
        dukeList.remove(taskIndex);
    }

    /**
     * Method to get a task from a specific index.
     * @param taskIndex The task index of the task needed.
     * @return The task needed.
     */
    public static Task getTask(int taskIndex) {
        return dukeList.get(taskIndex);
    }
}
