package duke.component;

import java.util.ArrayList;

import duke.task.Task;

/**
 * A class to represent a list of Tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Class constructor.
     * Returns empty ArrayList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the list of tasks
     * @return ArrayList of Task
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a new task to task list
     * @param task Task class
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task in list at the specified index.
     *
     * @param index Integer
     */
    public void removeTaskByIndex(int index) {
        tasks.remove(index);
    }

    /**
     * Returns task in list at the specified index.
     * @param index Integer
     * @return Task class
     */
    public Task getTaskByIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Set the list to a new ArrayList.
     *
     * @param tasks ArrayList of Task
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns number of tasks in list.
     * @return Integer
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

}
