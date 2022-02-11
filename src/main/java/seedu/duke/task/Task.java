package seedu.duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Used to create task that is added to {@link TaskList}.
 */
public abstract class Task {
    /**
     * taskName refers to the name of the task.
     */
    private final String taskName;
    /**
     * done records if a task has been marked as complete.
     */
    private final boolean isDone;
    private final LocalDateTime date;

    /**
     * Constructor for a task.
     * @param name refers to the task name
     */
    Task(String name) {
        this.taskName = name;
        this.isDone = false;
        //do null pointer exception check
        this.date = null;
    }

    /**
     * Constructor for a task that consists of task name and boolean done for task status.
     * @param name refers to the task name
     * @param doneStatus refers to the boolean that tracks if a task is complete
     */
    Task(String name, boolean doneStatus, LocalDateTime date) {
        this.taskName = name;
        this.isDone = doneStatus;
        this.date = date;
    }

    /**
     * Checks whether the task has been completed.
     * @return boolean of this done.
     */
    public boolean isDone() {
        return isDone;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public String getFormattingDateString() {
        try {
            String indicator = (this.date.getHour() >= 12) ? "pm" : "am";
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm ")) + indicator;
        } catch (NullPointerException e) {
            return "";
        }
    }

    /**
     * Marks task as done.or undone.
     * @return new Task with done attribute as the boolean parameter
     */
    public abstract Task changeTaskStatus(boolean isDone);

    public abstract String getTaskType();

    /**
     * prints task.
     * @return String with task type, status and name
     */
    @Override
    public String toString() {
        String marked = this.isDone() ? "X" : " ";
        return String.format("[%s] %s", marked, this.taskName);
    }
}
