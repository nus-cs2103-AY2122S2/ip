package duke.helptool;

import java.util.ArrayList;
import java.util.Objects;

import duke.task.Task;

/**
 * The type Task list.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Instantiates a new Task list.
     *
     * @param arrayList the array list
     */
    public TaskList(ArrayList<Task> arrayList) {
        this.taskList = Objects.requireNonNullElseGet(arrayList, ArrayList::new);
    }

    /**
     * Instantiates a new Task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }


    /**
     * Add task.
     *
     * @param task the task
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Delete task.
     *
     * @param index the index
     */
    public void delete(int index) {
        assert index < this.taskList.size() : "index is within bound";
        this.taskList.remove(index);
    }

    /**
     * Get task at index.
     *
     * @param index the index
     * @return the task
     */
    public Task getTask(int index) {
        assert index < this.taskList.size() : "index is within bound";
        return this.taskList.get(index);
    }

    /**
     * Get size int.
     *
     * @return the int
     */
    public int getSize() {
        return this.taskList.size();
    }


}
