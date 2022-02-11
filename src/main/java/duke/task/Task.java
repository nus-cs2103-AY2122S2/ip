package duke.task;

import java.time.LocalDate;

public class Task {
    private final String task;
    private final boolean isDone;

    /**
     * Represents Task class.
     *
     * @param task tasks for task.
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Changes status by generating new Task.
     *
     * @param task tasks for task.
     * @param isDone done status.
     */
    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    public Task mark() {
        return new Task(task, true);
    }

    public Task unmark() {
        return new Task(task, false);
    }

    public String saveData() {
        return task;
    }

    public LocalDate getDate() {
        return null;
    }

    public String getTask() {
        return task;
    }

    public int getDoneStatus() {
        return isDone ? 1 : 0;
    }

    @Override
    public String toString() {
        return isDone ? "[\u2713] " + task : "[\u2003] " + task;
    }
}
