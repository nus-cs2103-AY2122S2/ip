package duke.task;

import java.util.ArrayList;

/**
 * Represents a list of Tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList containing Tasks in taskList.
     *
     * @param taskList List of Tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructor for empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to taskList.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Returns size of taskList.
     *
     * @return  Size of taskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets a specified Task from taskList.
     *
     * @param n Index of Task to get.
     * @return Task specified by n.
     */
    public Task get(int n) {
        assert (n > 0 && n < taskList.size()) : "WHAT TASK YOU REFERRING TO AH? HELLO YOU BETTER WAKE UP YOUR IDEA!";

        return taskList.get(n);
    }

    /**
     * Removes a specified Task from taskList.
     *
     * @param n Index of Task to remove.
     * @return Task specified by n.
     */
    public Task remove(int n) {
        return taskList.remove((n));
    }

    /**
     * Checks if taskList is empty.
     *
     * @return Boolean stating if taskList is empty.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }


    /**
     * Prints out all tasks in taskList.
     *
     * @return String of all tasks.
     */
    public String printTasks() {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            output += "  " + (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        return output;
    }
}
