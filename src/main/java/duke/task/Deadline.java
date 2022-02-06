package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * It has a date field, and an optional time field.
 *
 * @author Terng Yan Long
 */
public class Deadline extends Task {
    private LocalDate dueDate;
    private LocalTime dueTime;

    /**
     * Instantiates a new instance of task that has a deadline.
     * By default, the new task is set to "not done".
     *
     * @param description Describes what needs to be done in this task.
     * @param dueDate Specifies the date that this task has to be done by.
     * @param dueTime Specifies the time that this task has to be done by.
     */
    public Deadline(String description, LocalDate dueDate, LocalTime dueTime) {
        super(description);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    /**
     * Prepends a checkbox "[D]" to the front of the task, which indicates the type of task.
     *
     * @return String containing a type icon that is prepended in front of the task description.
     */
    @Override
    public String toString() {
        String formattedDate = dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTime = "";
        if (dueTime != null) {
            formattedTime = " " + dueTime.toString();
        }
        return "[D]" + super.toString() + " (by: " + formattedDate + formattedTime + ")";
    }
}
