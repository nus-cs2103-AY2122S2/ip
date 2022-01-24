package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a task that has a deadline.
 */
public class Deadline extends Task {

    private LocalDate deadline;

    /**
     * Initialises a Deadline task with a specified
     * description and deadline.
     *
     * @param description the description of this Deadline task
     * @param time the deadline of this Deadline task
     */
    public Deadline(String description, LocalDate time) {
        super(description);
        this.deadline = time;
    }

    /**
     * Returns the deadline of this Deadline task as a String
     * in yyyy-MM-dd format.
     *
     * @return the deadline as a String in yyyy-MM-dd format
     */
    public String getDeadline() {
        return this.deadline.toString();
    }

    /**
     * Returns the string representation of this Deadline task.
     *
     * @return the string representation of this Deadline task.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[D]")
                .append(super.toString())
                .append(" (by: ")
                .append(this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy")))
                .append(")");
        return sb.toString();
    }
}
