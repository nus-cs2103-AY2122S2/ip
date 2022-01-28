import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks
 */
public class TaskList {
    List<Task> taskList;

    /**
     * Constructor
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public boolean add(Task e) {
        return taskList.add(e);
    }

    /**
     * Returns element at index
     *
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Returns size of taskList
     *
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Removes element at index
     *
     */
    public Task remove(int index) {
        return taskList.remove(index);
    }

    /**
     * Returns String representation of the TaskList
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