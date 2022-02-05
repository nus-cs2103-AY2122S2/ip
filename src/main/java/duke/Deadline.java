package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a due date (deadline).
 *
 * @author joey-chance
 * @version 1.0
 * @since 2022-02-05
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Returns a Deadline object with a description of the task and when it is due.
     *
     * @param description description of the task to be done
     * @param date due date of task
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, LocalDate date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    /**
     * This method is used to format a Deadline object into a String which can then be stored in the text file.
     *
     * @return This returns the String which details the task and the due date.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +")";
    }
}
