package seedu.duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    Deadline(Deadline oldDeadline, boolean done) {
        super(oldDeadline.getTaskName(), done, oldDeadline.getDate());
    }

    public Deadline(String taskName, boolean doneStatus, LocalDateTime date) {
        super(taskName,doneStatus, date);
    }

    /**
     * returns a new Task with the specified attribute 'done' based on boolean 'status'.
     * @param status to provide boolean
     * @return new Task with specified boolean for attribute 'done'
     */
    @Override
    public Task changeTaskStatus(boolean status) {
        return new Deadline(this, status);
    }

    @Override
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * prints the task string with [D] to annotate.
     * @return String task information
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)",this.getFormattingDateString());
    }
}
