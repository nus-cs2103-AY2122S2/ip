package siri;

import java.util.ArrayList;

//TaskList.java reused and edited from Brigette Santoso E0564307
/**
 * Contains the tasklist, and has the operations such as adding/deleting a task to/from the list.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructs a new Arraylist of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initialises the tasks of this tasklist to the given tasks.
     *
     * @param tasks Tasks inside the tasklist.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns an arraylist of tasks.
     *
     * @return Arraylist of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index Target index of task to be marked.
     */
    public void markTask(int index) {
        assert index >= 0 && index <= tasks.size();
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index Target index of task to be unmarked.
     */
    public void unmarkTask(int index) {
        assert index >= 0 && index <= tasks.size();
        tasks.get(index).markAsNotDone();
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index Target index of task to be deleted.
     */
    public void deleteTask(int index) {
        assert index >= 0 && index <= tasks.size();
        tasks.remove(index);
    }

    /**
     * Adds task to the Tasklist.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        assert task != null;
        tasks.add(task);
    }

}
