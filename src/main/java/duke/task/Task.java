package duke.task;

import java.time.LocalDate;

public class Task {
    private final String task;
    private final boolean isDone;
    private final Priority priority;

    /**
     * Represents Task class.
     *
     * @param task tasks for task.
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
        this.priority = Priority.NORMAL;
    }

    /**
     * Changes status by generating new Task.
     *
     * @param task   tasks for task.
     * @param isDone done status.
     */
    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
        this.priority = Priority.NORMAL;
    }

    /**
     * Changes status by generating new Task.
     *
     * @param task     tasks for task.
     * @param isDone   done status.
     * @param priority priority status.
     */
    public Task(String task, boolean isDone, Priority priority) {
        this.task = task;
        this.isDone = isDone;
        this.priority = priority;
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

    public Task setPriority(Priority priority) {
        return new Task(task, isDone, priority);
    }

    public String getPriorityText() {
        String priorityText = "";
        switch (priority) {
        case LOW:
            priorityText = "LOW ";
            break;
        case NORMAL:
            priorityText = "NORM";
            break;
        case HIGH:
            priorityText = "HIGH";
            break;
        default:
            assert false : "Unknown priority";
        }
        return priorityText;
    }

    @Override
    public String toString() {
        int maxLength = 20;
        String taskText = String.format("%1$-" + maxLength + "s", task);
        String isDoneText = isDone ? "[\u2713]" : "[\u2003]";
        return isDoneText + "[" + getPriorityText() + "] " + taskText;
    }
}
