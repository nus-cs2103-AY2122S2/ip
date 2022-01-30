package main.duke.task;

import main.duke.storage.Storage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Stores a list of tasks, and performs actions on them.
 */
public class TaskManager {

    // A list of tasks stored.
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Initializes the task manager, by reading the file to the tasks list.
     *
     * @param storage The storage to read from.
     */
    public TaskManager(Storage storage) {

        try {
            this.tasks = storage.readTasks();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }


    /**
     * Marks the status of the task as done.
     *
     * @param task The task to be operated on.
     */
    public static void markAsDone(Task task) {
        task.markAsDone();
    }

    /**
     * Marks the status of the task as not done.
     *
     * @param task The task to be operated on.
     */
    public static void markAsNotDone(Task task) {
        task.markAsNotDone();
    }

    /**
     * Lists all the tasks.
     *
     * @return The list of all the tasks.
     */
    public ArrayList<Task> listOfTasks() {

        return this.tasks;
    }

    /**
     * Adds a task into the list.
     *
     * @param task The task to be added.
     * @return The message after the task is added.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return "added: " + task.toString();
    }

    /**
     * Gets the task with the specified index.
     *
     * @param index The index of task to be retrieved (1-indexed).
     * @return The retrieved task.
     */
    public Task getTaskIndex(int index) {
        return this.tasks.get(index - 1);
    }

    /**
     * Checks the validity of a task index.
     *
     * @param index An index of task. (1-indexed)
     * @return True if the index is valid, false otherwise.
     */
    public boolean isValidIndex(int index) {

        return (index > 0 && index <= this.tasks.size());
    }

    /**
     * Removes the task with specified index.
     *
     * @param toDeleteIndex The index of the task to be deleted. (1-indexed)
     */
    public void deleteIndex(int toDeleteIndex) {

        this.tasks.remove(toDeleteIndex - 1);
    }

    /**
     * Write the current tasks back to the storage.
     *
     * @param storage The destination storage
     */
    public void writeBack(Storage storage) {

        storage.writeTasks(tasks);
    }
}
