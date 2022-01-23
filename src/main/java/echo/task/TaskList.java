package echo.task;

import java.util.ArrayList;

/**
 * This class encapsulates the list of tasks associated with Echo.
 */
public class TaskList {
    /** ArrayList of task. */
    private final ArrayList<Task> TASKS;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.TASKS = new ArrayList<>();
    }

    /**
     * Constructor for TaskList class.
     *
     * @param taskList TaskList.
     */
    public TaskList(TaskList taskList) {
        this.TASKS = taskList.TASKS;
    }

    /**
     * Add task to task list.
     *
     * @param task Task.
     */
    public void add(Task task) {
        this.TASKS.add(task);
    }

    /**
     * Delete task.
     *
     * @param i Task index.
     */
    public void delete(int i) {
        this.TASKS.remove(i);
    }

    /**
     * Mark task.
     *
     * @param i Task index.
     */
    public void mark(int i) {
        this.TASKS.get(i).mark();
    }

    /**
     * Unmark task.
     *
     * @param i Task index.
     */
    public void unmark(int i) {
        this.TASKS.get(i).unmark();
    }

    /**
     * String representation of task for saving.
     *
     * @param i Task index.
     */
    public String saveTaskFormat(int i) {
        return this.TASKS.get(i).saveFormat();
    }

    /**
     * Returns size of TaskList.
     *
     * @return Size of TaskList.
     */
    public int size() {
        return TASKS.size();
    }

    /**
     * Return string representation of task index and description.
     *
     * @param i Task index.
     *
     * @return String representing task status.
     */
    public String taskStatus(int i) {
        return String.format("%d. %s", i + 1, TASKS.get(i).toString());
    }

    /**
     * Check if TaskList is empty.
     *
     * @return True if empty; Otherwise false.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

}
