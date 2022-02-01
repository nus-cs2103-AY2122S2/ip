package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with its description and deadline.
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * Creates a Deadline task with its description and deadline.
     * 
     * @param description description of deadline task.
     * @param date date is the deadline of task.
     */
    public Deadline(String description, LocalDate date) {
        super(description, 'D');
        this.date = date;
    }

    /**
     * Gets the deadline of this task.
     * 
     * @return Returns the deadline of this task.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns a formatted string of a Deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                super.getStatusIcon(),
                super.getDescription(),
                this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
