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
     *
     * @return a list of the user's task.
     */
    public String listTasks() {
        StringBuilder stringBuilder = new StringBuilder();
        if (taskList.size() == 0) {
            stringBuilder.append("You have no tasks!");
        } else {
            stringBuilder.append("The tasks on your list. Get it done!\n");
            for (int i = 0; i < taskList.size(); i++) {
                int taskNumber = i + 1;
                stringBuilder.append("  " + taskNumber + ". " + taskList.get(i));
                if (i != taskList.size() - 1) {
                    stringBuilder.append("\n");
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Displays the tasks in the list with a given keyword.
     *
     * @param keyword the search keyword used to find the tasks.
     * @return a list of the user's task.
     */
    public String listTasks(String keyword) {
        StringBuilder stringBuilder = new StringBuilder();
        if (taskList.size() == 0) {
            stringBuilder.append("You have no tasks!");
        } else {
            stringBuilder.append("Here are the task(s) that contain(s) your keyword.\n");
            for (int i = 0; i < taskList.size(); i++) {
                int taskNumber = i + 1;
                Task task = taskList.get(i);
                if (task.getDescription().contains(keyword)) {
                    stringBuilder.append("  " + taskNumber + ". " + task);
                    if (i != taskList.size() - 1) {
                        stringBuilder.append("\n");
                    }
                }
            }
        }
        return stringBuilder.toString();
    }
}
