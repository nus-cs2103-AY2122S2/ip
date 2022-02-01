package mcbot.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class implements the Task class with additional deadline information. 
 */
public class Deadline extends Task {
    protected DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
    protected DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;

    /**
     * Constructs an deadline task with a task name and deadline date. 
     *
     * @param taskName The name of the deadline.
     * @param date The date of the deadline.
     */
    public Deadline(String taskName, LocalDate date) {
        super(taskName);
        this.deadlineDate = date;
    }

    /**
     * Constructs a deadline task with a task name, deadline date and time. 
     *
     * @param taskName The name of the deadline.
     * @param deadlineDate The date of the deadline.
     * @param deadlineTime The time of the deadline.
     */
    public Deadline(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(taskName);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Returns the icon representing the task.
     * "D" is used to represent an deadline task.
     *
     * @return The task icon representation.
     */
    @Override
    public String getTaskIcon() {
        return "D";
    }

    /**
     * Returns a String to be stored into the data text file.
     * The format is D | 0 | taskName | date | time .
     * 0 represents incomplete task.
     * 1 represents completed task.
     *
     * @return The data representation as a String to be stored.
     */
    @Override
    public String toDataString() {
        String isDone = super.isMarked() ? "1" : "0";
        if (deadlineTime != null) {
            return getTaskIcon() + " | " + isDone + " | " + taskName 
                    + " | " + deadlineDate + " | " + deadlineTime;
        } else {
            return getTaskIcon() + " | " + isDone + " | " + taskName + " | " + deadlineDate;
        }
    }

    /**
     * Returns a String that describes the Task.
     * String contains the task icon, status icon, task name,
     * the date and time of the deadline. 
     *
     * @return The string describing the task.
     */
    @Override
    public String toString() {
        if (deadlineTime != null) {
            return "[" + getTaskIcon() + "][" + super.getStatusIcon() + "] " +
                    super.taskName + " (by: " + dateFormatter.format(deadlineDate)
                    + " " + timeFormatter.format(deadlineTime) + ")";
        } else {
            return "[" + getTaskIcon() + "][" + super.getStatusIcon() + "] " +
                    super.taskName + " (by: " + dateFormatter.format(deadlineDate) + ")";
        }
    }
}
