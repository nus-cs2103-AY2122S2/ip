package seedu.duke.task;

import java.time.LocalDateTime;

/**
 * Used when user wants to create a {@link Task} that has a end date but no start date.
 */
public class Deadline extends Task {
    private final String taskType = "D";

    /**
     * Creates a deadline.
     * @param taskName for task name
     * @param date for end date
     */
    public Deadline(String taskName, LocalDateTime date) {
        super(taskName, false, date);
    }

    /**
     * Used to help adjust the done status.
     * @param oldDeadline to extract task name and date
     * @param done for specified boolean
     */
    Deadline(Deadline oldDeadline, boolean isDone) {
        super(oldDeadline.getTaskName(), isDone, oldDeadline.getDate());
    }

    public Deadline(String taskName, boolean isDone, LocalDateTime date) {
        super(taskName,isDone, date);
    }

    /**
     * returns a new Task with the specified attribute 'done' based on boolean 'status'.
     * @param isDone to provide boolean on whether the task has been marked as done
     * @return new Task with specified boolean for attribute 'done'
     */
    @Override
    public Task changeTaskStatus(boolean isDone) {
        return new Deadline(this, isDone);
    }

    @Override
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)",this.getFormattingDateString());
    }
}
