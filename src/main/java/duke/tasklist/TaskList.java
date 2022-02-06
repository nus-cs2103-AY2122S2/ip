package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a TaskList that contains all added tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor for a TaskList object.
     *
     * @param arr An ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> arr) {
        this.tasks = arr;
    }

    /**
     * Gets a task at a particular index.
     *
     * @param index The index of the task to return.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Gets the total number of tasks in the TaskList.
     *
     * @return The total number of tasks.
     */
    public int getCount() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added to the TaskList.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void addTask(Task task, int index) {
        this.tasks.add(index, task);
    }

    /**
     * Removes a task at a particular index.
     *
     * @param index The index of the task to remove.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Removes all tasks in the list.
     */
    public void clearAllTask() {
        this.tasks.clear();
    }

    /**
     * Returns a task list with tasks that contains a given keyword.
     *
     * @param keyword The given keyword to be searched for.
     * @return A task list with matching tasks.
     */
    public TaskList filterByWord(String keyword) {
        ArrayList<Task> resArr = new ArrayList<Task>();
        int size = this.getCount();
        for (int i = 0; i < size; i++) {
            Task task = this.tasks.get(i);
            if (task.getName().contains(keyword)) {
                resArr.add(task);
            }
        }
        return new TaskList(resArr);
    }

}
