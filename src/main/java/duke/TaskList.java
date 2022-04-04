package duke;

import java.util.ArrayList;

/**
 * TaskList class, which contains Tasks and has methods that interact with Tasks.
 *
 * @author Benjamin Koh
 */

public class TaskList {
    private static ArrayList<Task> taskList;

    /**
     * Constructor for a new instance of a TaskList
     */

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Add method to add a new task to the current taskList
     *
     * @param task new task to add into the current taskList
     */

    public static void add(Task task) {
        taskList.add(task);
    }


    /**
     * Method to get the size of the current taskList
     *
     * @return size of the current taskList
     */

    public int getSize() {
        return taskList.size();
    }

    /**
     * Get method to get a certain task inside the taskList
     *
     * @param i index of the task that is required
     * @return task in the inputted index
     */

    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Remove method to remove a specific task inside the taskList
     *
     * @param numToDelete index of the task that needs to be removed
     */

    public void remove(int numToDelete) {
        this.taskList.remove(numToDelete - 1);
    }
}
