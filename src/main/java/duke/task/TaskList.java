package duke.task;

import java.util.ArrayList;

/**
 * Represents a list of the tasks the user has.
 */
public class TaskList {
    protected ArrayList<Task> taskList;

    /**
     * Constructor for a TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Gets a specific task from the list.
     *
     * @param index the index of the task to be accessed.
     * @return the task to be accessed.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param index the index of the task to be removed.
     */
    public void removeTask(int index) {
        this.taskList.remove(index);
    }

    /**
     * Displays all the tasks in the list.
     */
    public void listTasks() {
        if (taskList.size() == 0) {
            System.out.println("You have no tasks!");
        } else {
            System.out.println("The tasks on your list. Get it done!");
            for (int i = 0; i < taskList.size(); i++) {
                int taskNumber = i + 1;
                System.out.println("  " + taskNumber + ". " + taskList.get(i));
            }
        }
    }

    /**
     * Displays the tasks in the list with a given keyword.
     *
     * @param keyword the search keyword used to find the tasks.
     */
    public void listTasks(String keyword) {
        if (taskList.size() == 0) {
            System.out.println("You have no tasks!");
        } else {
            System.out.println("Here are the task(s) that contain(s) your keyword.");
            for (int i = 0; i < taskList.size(); i++) {
                int taskNumber = i + 1;
                Task task = taskList.get(i);
                if (task.getDescription().contains(keyword)) {
                    System.out.println("  " + taskNumber + ". " + task);
                }
            }
        }
    }
}
