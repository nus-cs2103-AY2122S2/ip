package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * Represents a task with a time to do it by. A <code>Deadline</code> object corresponds to the task represented by
 * a String name and a String by which specified the date to do it by.
 */
public class Deadline extends duke.task.Task {
    protected LocalDate by;

    /**
     * Returns a new instance of a <code>Deadline</code> object with the specified name and date to do it by.
     *
     * @param name Name of the deadline task.
     * @param by   Date to do the deadline task by.
     */
    public Deadline(String name, String by) throws DateTimeParseException {
        super(name);
        System.out.println(by);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException storedException) {
            this.by = LocalDate.parse(by, DateTimeFormatter.ofLocalizedDate(FormatStyle.valueOf("MMM dd yyyy")));
        }
        // to add today, tomorrow, and all 3's time included format
    }

    /**
     * Returns a String representation of the <code>Deadline</code> object to be read by the users in <code>Duke</code>.
     *
     * @return User-friendly string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns a String representation of the <code>Deadline</code> object to be saved in the hard drive for future
     * uses. It is more concise and computer-friendly than the <code>toString</code> method.
     *
     * @return Computer-friendly string representation of the deadline for storing of data.
     */
    @Override
    public String toText() {
        return "D | " + (this.getIsDone() ? 1 : 0) + " | " + this.getName() + " | " + this.by + "\n";
    }

    @Override
    public LocalDate getDate() {
        return this.by;
    }
}
