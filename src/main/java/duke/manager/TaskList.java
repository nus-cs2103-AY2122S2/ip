package duke.manager;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;


/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Default constructor to intialise an empty ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * A constructor to store the list of tasks.
     *
     * @param tasks An ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a Task object into the TaskList.
     *
     * @param t A Task.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Removes a task from the TaskList.
     *
     * @param taskNo Index of the Task to be deleted.
     * @return The Task to be deleted.
     * @throws DukeException If an invalid index in the list was given.
     */
    public Task delete(int taskNo) throws DukeException {
        if (!isValidTaskNo(taskNo)) {
            throw new DukeException("☹ OOPS!!! Invalid task number.");
        }
        return tasks.remove(taskNo);
    }

    /**
     * Retrieves, without removing, a Task from the TaskList.
     *
     * @param taskNo Index of the Task to be retrieved.
     * @return The Task to be retrieved.
     * @throws DukeException If an invalid index in the list was given.
     */
    public Task getTask(int taskNo) throws DukeException {
        if (!isValidTaskNo(taskNo)) {
            throw new DukeException("☹ OOPS!!! Invalid task number.");
        }
        return tasks.get(taskNo);
    }

    /**
     * Returns the number of Tasks in the TaskList.
     *
     * @return The number of Tasks in the TaskList.
     */
    public int numOfTasks() {
        return tasks.size();
    }

    /**
     * Returns the actual ArrayList that stores the Tasks.
     *
     * @return The ArrayList that stores the Tasks.
     */
    public ArrayList<Task> toArrayList() {
        return this.tasks;
    }

    /**
     * Marks the task at the index of the TaskList as complete.
     *
     * @param taskNo The index of the task to be marked as complete.
     * @throws DukeException If an invalid index in the list was given.
     */
    public void markDone(int taskNo) throws DukeException {
        if (!isValidTaskNo(taskNo)) {
            throw new DukeException("☹ OOPS!!! Invalid task number.");
        }
        tasks.get(taskNo).markDone();
    }

    /**
     * Marks the task at the index of the TaskList as incomplete.
     *
     * @param taskNo The index of the task to be marked as incomplete.
     * @throws DukeException If an invalid index in the list was given.
     */
    public void markUndone(int taskNo) throws DukeException {
        if (!isValidTaskNo(taskNo)) {
            throw new DukeException("☹ OOPS!!! Invalid task number.");
        }
        tasks.get(taskNo).markUndone();
    }

    /**
     *
     * Returns a boolean depending on the taskNo given and whether it is within range of the ArrayList.
     *
     * @param taskNo The index to be checked if valid.
     * @return True if the taskNo is within range of the ArrayList and false otherwise.
     */
    public boolean isValidTaskNo(int taskNo) {
        if (taskNo > tasks.size() || taskNo < 0) {
            return false;
        } else {
            return true;
        }
    }

}
