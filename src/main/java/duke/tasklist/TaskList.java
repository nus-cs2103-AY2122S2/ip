package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores the tasks within the program while running.
 */
public class TaskList {

    /** Stores the tasks */
    private List<Task> tasks;

    /**
     * Instantiates the instance when no data file is found.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Instantiates the instance when taking in a data file.
     *
     * @param savedTasks ArrayList of saved tasks in the data file.
     */
    public TaskList(ArrayList<Task> savedTasks) {
        this.tasks = savedTasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Delete a task from the task list.
     *
     * @param t The task to be deleted.
     */
    public void deleteTask(Task t) {
        this.tasks.remove(t);
    }

    /**
     * Returns the contents of the task list for display to the user.
     *
     * @return String of all tasks in the task list.
     */
    public String getTasksMsg() {
        StringBuilder s = new StringBuilder();
        for (Task t: this.tasks) {
            s.append(t.toString()).append("\n");
        }
        return s.toString();
    }

    /**
     * Returns the contents of the task list for storage into the data file.
     *
     * @return String of all tasks in the task list.
     */
    public String getTaskStore() {
        StringBuilder s = new StringBuilder();
        for (Task t: this.tasks) {
            s.append(t.writeToFile()).append("\n");
        }
        return s.toString();
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of task ArrayList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index The index of the task in the task list.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }
}
