package jarvis;

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

    public LocalDate getDate() {
        return this.date;
    }

    /**
     * This method is used to format a Deadline object into a String which can then be stored in the text file.
     *
     * @return This returns the String which details the task and the due date.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * This method is used to compare if two Task objects are equal or not. Two Task objects are equal if
     * they have the same description and same status.
     *
     * @param o The object to compare with
     * @return boolean This returns true if they are equal and false if they are not.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deadline)) {
            return false;
        }
        Deadline task = (Deadline) o;
        boolean sameDescription = task.getDescription().equals(this.getDescription());
        boolean sameStatus = (task.isDone() == this.isDone());
        boolean sameDate = (task.getDate().equals(this.getDate()));
        return sameDescription && sameStatus && sameDate;
    }
}
