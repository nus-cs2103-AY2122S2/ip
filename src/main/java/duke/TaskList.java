package duke;

import java.util.ArrayList;

/**
 * This is a TaskList class that handles the operations to the
 * Tasks in this Duke application
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<>();

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the particular task
     * @param i is the specified index of Task in the arraylist of Tasks
     */
    public Task getParticularTask(int i) {
        return tasks.get(i);
    }

    /**
     * Returns the size of the current TaskList
     */
    public int getTaskListSize() {
        return tasks.size();
    }

    /**
     * Adds a Task into the TaskList
     * @param task is the Task object
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes Tasks from the TaskList
     * @param i is the specified index of Task in the arraylist of Tasks
     */
    public Task deleteTask(int i) throws DukeException {
        return tasks.remove(i - 1);
    }

    /**
     * Marks the Task as done by given index input.
     * Index is based on the position the Task is in the TaskList
     * @param i is the specified index of Task in the arraylist of Tasks
     */
    public Task markTask(int i) {
        Task task = tasks.get(i - 1);
        task.setDone(1);
        return task;
    }

    /**
     * Unmarks the Task as undone by given index input.
     * Index is based on the position the Task is in the TaskList
     * @param i is the specified index of Task in the arraylist of Tasks
     */
    public Task unmarkTask(int i) {
        Task task = tasks.get(i - 1);
        task.setDone(0);
        return task;
    }
}
