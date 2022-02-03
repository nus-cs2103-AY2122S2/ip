package duke.tasks;

import java.io.Serializable;
import java.util.ArrayList;

import duke.exceptions.DukeException;

/**
 * Represents a container that manages the tasks in the program.
 * Stores an internal array list which contains the tasks objects.
 */
public class TaskList implements Serializable {

    protected ArrayList<Task> tasks;

    /**
     * Creates an instance of a TaskList object and initializes the
     * internal array list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the size of the internal array list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Removes the task from the internal array list as specified by
     * the index. Throws an exception if the provided index is invalid.
     *
     * @param idx the index of the task to be removed.
     * @return the string representation of the removed task.
     */
    public String remove(int idx) throws DukeException {
        try {
            return tasks.remove(idx).toString();
        } catch (Exception e) {
            throw new DukeException("Invalid input! This task number does not exist.");
        }
    }

    /**
     * Marks/Unmarks the task in the internal array list as specified by
     * the index. Throws an exception if the provided index is invalid.
     *
     * @param idx the index of the task to be removed.
     * @param isMark the boolean that specifies if a task is to be marked or unmarked.
     * @return the string representation of the marked/unmarked task.
     */
    public String markTask(int idx, boolean isMark) throws DukeException {
        try {
            Task task = tasks.get(idx);
            task.setCompleted(isMark);
            return task.toString();
        } catch (Exception e) {
            throw new DukeException("Invalid input! This task number does not exist.");
        }
    }

    /**
     * Creates a todo task and adds it into the internal array list. Throws an exception
     * if the token provided is invalid.
     *
     * @param token string array containing information required to create the todo.
     * @return the string representation of the created todo.
     */
    public String addTodoTask(String[] token) throws DukeException {
        Todo task = Todo.createTask(token);
        tasks.add(task);
        return task.toString();
    }

    /**
     * Creates a deadline task and adds it into the internal array list. Throws an exception
     * if the token provided is invalid.
     *
     * @param token string array containing information required to create the deadline.
     * @return the string representation of the created deadline.
     */
    public String addDeadlineTask(String[] token) throws DukeException {
        Deadline task = Deadline.createTask(token);
        tasks.add(task);
        return task.toString();
    }

    /**
     * Creates an event task and adds it into the internal array list. Throws an exception
     * if the token provided is invalid.
     *
     * @param token string array containing information required to create the event.
     * @return the string representation of the created event.
     */
    public String addEventTask(String[] token) throws DukeException {
        Event task = Event.createTask(token);
        tasks.add(task);
        return task.toString();
    }

    /**
     * Returns an array list of the String representation of the tasks currently in
     * the internal array list.
     *
     * @return the string representation of all the tasks in the internal array list.
     */
    public ArrayList<String> list() {
        ArrayList<String> taskSet = new ArrayList<>();
        for (Task task : tasks) {
            taskSet.add(task.toString());
        }
        return taskSet;
    }

    /**
     * Returns an array list of the String representation of the tasks currently in
     * the internal array list with description similar to the given search String.
     *
     * @param searchString the String that is used to determine which task representations
     *                     are returned.
     * @return the string representation of the tasks with description similar to the search String.
     */
    public ArrayList<String> search(String searchString) {
        ArrayList<String> taskSet = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(searchString)) {
                taskSet.add(task.toString());
            }
        }
        return taskSet;
    }
}
