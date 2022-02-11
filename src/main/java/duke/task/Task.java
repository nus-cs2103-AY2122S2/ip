package duke.task;

import java.time.LocalDate;

public class Task {
    private final String task;
    private final boolean done;

    /**
     * Task class.
     *
     * @param task tasks for task.
     */
    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * Task class for modify status.
     *
     * @param task tasks for task.
     * @param done done status.
     */
    public Task(String task, boolean done) {
        this.task = task;
        this.done = done;
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
        return done ? 1 : 0;
    }

    @Override
    public String toString() {
        return done ? "[\u2713] " + task : "[\u2003] " + task;
    }
}
