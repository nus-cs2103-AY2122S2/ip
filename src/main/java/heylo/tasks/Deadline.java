package heylo.tasks;

import java.time.LocalDate;

import heylo.util.DateFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDate deadline;

    /**
     * Creates a task with a deadline.
     *
     * @param description String description of the task.
     * @param deadline    String date of the deadline.
     */
    public Deadline(String description, String deadline) {
        super(description);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (Exception e) {
            this.deadline = null;
        }
    }

    /**
     * Converts the deadline task and its data to string format.
     *
     * @return String deadline.
     */
    @Override
    public String toString() {
        return " [D]"
                + super.toString()
                + "\t (by " + DateFormatter.formatDateInLongForm(deadline) + ")";
    }
}
