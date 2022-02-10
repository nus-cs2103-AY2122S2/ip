package duke.main;

import java.util.ArrayList;
import java.util.List;

import duke.tasks.Task;

/**
 * TaskList class.
 * This class encapsulates the abstract List of Tasks.
 */
public class TaskList {
    private List<Task> toDoList;

    /**
     * Constructor for TaskList.
     * Simply instantiates a new ArrayList of Tasks, which is used
     * to keep track of all the Tasks.
     */
    public TaskList() {
        this.toDoList = new ArrayList<>();
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param t the Task to add
     */
    public void add(Task t) {
        this.toDoList.add(t);
    }

    /**
     * Removes a Task from the TaskList.
     *
     * @param index the index of the Task to be removed
     * @return the Task that has been removed
     */
    public Task remove(int index) {
        return this.toDoList.remove(index);
    }

    /**
     * Queries the number of items in the TaskList.
     *
     * @return the number of items in the TaskList
     */
    public int size() {
        return this.toDoList.size();
    }

    /**
     * Retrievs a Task from the TaskList.
     *
     * @param i the index of the Task to be retrieved
     * @return the retrieved Task
     */
    public Task get(int i) {
        return this.toDoList.get(i);
    }
}
