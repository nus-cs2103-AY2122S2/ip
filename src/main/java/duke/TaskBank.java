package duke;

import java.util.ArrayList;

/**
 * Represents a bank that contains tasks
 */
public class TaskBank {
    private static ArrayList<Task> bank = new ArrayList<Task>();

    /**
     * Construtor for TaskBank
     */
    public TaskBank() {}

    /**
     * Adds a task to the task bank
     * @param task The task object to be added
     */
    public static void addTask(Task task) {
        bank.add(task);
    }

    /**
     * Removes a task from the task bank
     * @param index The index of the task to be removed.
     */
    public static void deleteTask(int index) {
        bank.remove(index);
    }

    public static ArrayList<Task> getBank() {
        return bank;
    }
}
