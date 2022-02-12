package spike.task;

import java.util.ArrayList;

/**
 * Stores all the task objects.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Alternative constructor using an existing task arraylist.
     *
     * @param tasks task list stored in arraylist
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds the selected task.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes the selected task.
     *
     * @param task the task to be deleted
     */
    public void deleteTask(Task task) {
        this.tasks.remove(task);
    }

    /**
     * Returns all task in the form of arraylist
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }


    /**
     * Returns the number of tasks present
     */
    public int getListSize() {
        return this.getTasks().size();
    }
}
