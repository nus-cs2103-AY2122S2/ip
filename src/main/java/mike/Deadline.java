package mike;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task. A deadline is a task with a "by" date.
 */
public class Deadline extends Task {
    private final String endDate;
    private final LocalDate date;
    private static final String taskType = "D";

    private static LocalDate convertToDate(String endDate) {
        return LocalDate.parse(endDate);
    }

    /**
     * Constructor for deadline.
     *
     * @param name Deadline name.
     * @param endDate Deadline date.
     */
    public Deadline(String name, String endDate) {
        super(name);
        this.endDate = endDate;
        this.date = convertToDate(endDate);
    }

    /**
     * Constructor for deadline.
     *
     * @param name Deadline name.
     * @param endDate Deadline date.
     * @param isDone Whether deadline is marked as done.
     */
    public Deadline(String name, String endDate, boolean isDone) {
        super(name, isDone);
        this.endDate = endDate;
        this.date = convertToDate(endDate);
    }

    /**
     * Returns a new deadline object with the isDone field set to true.
     *
     * @return Deadline object with isDone set to true.
     */
    public Deadline markAsDone() {
        return new Deadline(this.name, this.endDate, true);
    }

    /**
     * Returns a new deadline object with the isDone field set to false.
     *
     * @return Deadline object with isDone set to false.
     */
    public Deadline markAsUndone() {
        return new Deadline(this.name, this.endDate, false);
    }

    /**
     * Returns the deadline as a String in a format that can be stored.
     *
     * @return String representation of deadline.
     */
    public String convertToStoredTaskFormat() {
        String doneIndicator = "false";
        if (super.isDone) {
            doneIndicator = "true";
        }
        String storedListFormat = String.format("%s|%s|%s|%s", taskType,
                doneIndicator, super.name, this.date);
        return storedListFormat;
    }

    /**
     * Returns a String representing the deadline object.
     *
     * @return String representing the deadline object.
     */
    @Override
    public String toString() {
        String doneMark;
        if (super.isDone) {
            doneMark = "X";
        } else {
            doneMark = " ";
        }
        String dateOutput = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[%s][%s] %s (by: %s)",
                taskType, doneMark, super.name, dateOutput);
    }
}
