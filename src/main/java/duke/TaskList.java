package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 *
 * @author sibinhho99-nus
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList.
     * @param e the task to be added.
     */
    public boolean add(Task e) {
        return taskList.add(e);
    }

    /**
     * Returns element at index.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Returns size of taskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Removes element at index.
     * @param index the index of the task to be removed.
     */
    public Task remove(int index) {
        return taskList.remove(index);
    }

    /**
     * Returns String representation of the duke.TaskList
     */
    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            list.append(String.format("%d.%s\n", i + 1, taskList.get(i)));
        }

        return list.toString().trim();
    }
}
