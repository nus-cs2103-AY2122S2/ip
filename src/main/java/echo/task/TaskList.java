package echo.task;

import java.util.ArrayList;

/**
 * This class encapsulates the list of tasks associated with Echo.
 */
public class TaskList {
    /** ArrayList of task. */
    private final ArrayList<Task> TASKS;

    public TaskList() {
        this.TASKS = new ArrayList<>();
    }

    public TaskList(TaskList taskList) {
        this.TASKS = taskList.TASKS;
    }

    public void add(Task task) {
        this.TASKS.add(task);
    }

    public void delete(int i) {
        this.TASKS.remove(i);
    }

    public void mark(int i) {
        this.TASKS.get(i).mark();
    }

    public void unmark(int i) {
        this.TASKS.get(i).unmark();
    }

    public String saveTaskFormat(int i) {
        return this.TASKS.get(i).saveFormat();
    }

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

    public boolean isEmpty() {
        return size() == 0;
    }

}
