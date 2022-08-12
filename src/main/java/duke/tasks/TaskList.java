package duke.tasks;

import java.util.ArrayList;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the
 * list. In other words it just contains ArrayList<Task> tasks, and some CRUD
 * operations.
 */
public class TaskList {
    // what are the methods we require from this class?
    // in Duke.java we have called this statement ==> tasks = new
    // TaskList(storage.load());
    // which means we should initialise the TaskList, and pass in the argument of
    // storage.load();

    private ArrayList<Task> tasks;

    /**
     * Note that from Duke.java, the storage.load() argument should return an
     * ArrayList<Task> so that we can copy that list directly to this class.
     * 
     * @param tasks - the tasks we get from duke.txt
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Second constructor for a blank ArrayList, indicating theres nothing in the
     * storage.
     * 
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    // will need to get the arrayList here
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * The CRUD operations to be performed on task.
     */

    /**
     * Deletes a task from the list.
     * 
     * @return ArrayList tasks
     */
    public void deleteFromTasks(int number) {
        tasks.remove(number);
    }

    /**
     * Add another task to the list.
     * 
     * @return ArrayList<Task> tasks
     */
    public void addToTasks(Task task) {
        tasks.add(task);
    }

    /**
     * This method will toggle the isDone status of the task,
     * and should take in an argument which marks the position of the Task in the
     * list.
     * 
     * @return not determined yet
     */
    // public ArrayList<Task> toggleIsDone() {
    // return this;
    // }
}
