package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> list;

    /**
     * Constructs an empty list of tasks.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Constructs a {@code TaskList} object from a list of tasks.
     * @param list the list of tasks
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a new task to the list of tasks.
     * @param t the new task to add
     * @return a new list of tasks with the new task added
     */
    public TaskList add(Task t) {
        ArrayList<Task> newList = list;
        newList.add(t);
        return new TaskList(newList);
    }

    /**
     * Retrieves the task at a specified index in the list of tasks.
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Replaces the task at a specified index in the list of tasks with a new task.
     * @param index the index of the task to be replaced
     * @param t the new task
     * @return a new list of tasks after the replacement
     */
    public TaskList set(int index, Task t) {
        ArrayList<Task> newList = list;
        newList.set(index, t);
        return new TaskList(newList);
    }

    /**
     * Removes the task at a specified index in the list of tasks.
     * @param index the index of the task to be replaced
     * @return a new list of tasks with the specified task deleted
     */
    public TaskList remove(int index) {
        ArrayList<Task> newList = list;
        newList.remove(index);
        return new TaskList(newList);
    }

    /**
     * Returns the number of tasks in the list of tasks.
     * @return the number of tasks in the list of tasks
     */
    public int size() {
        return list.size();
    }

}
