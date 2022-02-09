package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class.
 */
public class Deadline extends Task {

    private LocalDate date;

    /**
     * Constructs deadline.
     * @param description Task description.
     * @param isMarked Indicates whether task is marked/done.
     * @param date Deadline date
     */
    public Deadline(String description, boolean isMarked, LocalDate date) {
        super(description, isMarked);
        this.date = date;
    }

    /**
     * Constructs deadline.
     * @param description Task description.
     * @param date Deadline date
     */
    public Deadline(String description, LocalDate date) {
        this(description, false, date);
    }

    /**
     * Returns string representation of a deadline.
     * @return Deadline as a string
     */
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                super.isMarked(),
                super.getDescription(),
                DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
