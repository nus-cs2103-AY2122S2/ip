package duke;

import java.util.ArrayList;

/**
 * TaskList class, which contains Tasks and has methods that interact with Tasks.
 *
 * @author Benjamin Koh
 */

public class TaskList {
    private static ArrayList<Task> taskList;
//    private int taskListSize;

    /**
     * Constructor for a new instance of a TaskList
     */

    public TaskList() {
        this.taskList = new ArrayList<>();
//        this.taskListSize = 0;
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

    public int getTaskListSize() {
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
//        Task currTask = this.taskList.get(numToDelete - 1);
        this.taskList.remove(numToDelete - 1);
    }
}
