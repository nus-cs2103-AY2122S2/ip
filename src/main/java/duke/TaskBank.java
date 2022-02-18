package duke;

import java.util.ArrayList;

/**
 * Represents a bank that contains tasks
 */
public class TaskBank {
    private static ArrayList<Task> bank = new ArrayList<Task>();
    // private static Storage storage;

    /**
     * Construtor for TaskBank
     */
    public TaskBank(Storage storage) {
        // this.storage = storage;
        ArrayList<Task> memory = storage.readFile();
        for (int i = 0; i < bank.size(); i++) {
            bank.add(memory.get(i));
        }
    }

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

    /**
     * Updates a task's description
     * @param index
     * @param newDescription
     */
    public static void updateTask(int index, String newDescription) {
        bank.get(index).updateDescription(newDescription);
    }

    public static int getSize() {
        return bank.size();
    }

    public static ArrayList<Task> getBank() {
        return bank;
    }
}
