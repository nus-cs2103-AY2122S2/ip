package duke.task;

import java.util.ArrayList;

/**
 * Represents a list of <code>Tasks</code>. A <code>TaskList</code> object is represented by an ArrayList of Tasks.
 */
public class TaskList {
    private ArrayList<duke.task.Task> toDoList;

    /**
     * Returns a new instance of the <code>TaskList</code> object with the specified ArrayList.
     * @param arr ArrayList of tasks.
     */
    public TaskList(ArrayList<duke.task.Task> arr) {
        this.toDoList = arr;
    }

    /**
     * Returns the ArrayList of <code>Tasks</code>.
     * @return ArrayList of <code>Tasks</code>.
     */
    public ArrayList<duke.task.Task> getToDoList() {
        return toDoList;
    }

    /**
     * Adds a <code>Task</code> to the list.
     * @param task <code>Task</code> to be added to the list.
     */
    public void add(duke.task.Task task) {
        this.toDoList.add(task);
    }

    /**
     * Returns the <code>Task</code> at the specified index of the list.
     * @param idx Index of the task.
     * @return <code>Task</code> at the index.
     */
    public duke.task.Task get(int idx) {
        return this.toDoList.get(idx);
    }

    /**
     * Removes the <code>Task</code> at the index of the list and returns it.
     * @param idx Index of the task.
     * @return <code>Task</code> removed at the index.
     */
    public duke.task.Task remove(int idx) {
        return this.toDoList.remove(idx);
    }
}
