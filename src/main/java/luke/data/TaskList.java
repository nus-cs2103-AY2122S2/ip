package luke.data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import luke.data.tasks.Task;
import luke.storage.Storable;

/**
 * Implements a list for storing tasks.
 * It implements Storable to store the data in a file using StorageFile.
 */
public class TaskList implements Storable {
    private List<Task> taskList;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    @Override
    public List<String> getData() {
        List<String> list = new ArrayList<>();
        for (Task task : taskList) {
            list.add(String.format("%s | %s", task.getCommandString(),
                    task.isDone() ? "1" : "0"));
        }
        return list;
    }

    /**
     * Returns true if the task list is empty.
     *
     * @return True if the task list is empty.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Returns the size of the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the element at the specified index of the task list.
     *
     * @param index The specified index.
     * @return The task at the specified index of the task list.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Adds the specified task to the task list.
     *
     * @param task The specified task to add into the task list.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes and returns the task at the specified index of the task list.
     *
     * @param index The specified index.
     * @return The task removed at the specified index of the task list.
     */
    public Task remove(int index) {
        return taskList.remove(index);
    }

    public void setFiltered(Predicate<Task> predicate) {
        taskList.stream().forEach(x -> x.clearFilter());
        taskList.stream().forEach(x -> {
            if (!predicate.test(x)) {
                x.markAsFiltered();
            }
        });
    }
}
