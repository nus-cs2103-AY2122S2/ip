package mike;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline (subclass of task).
 */
public class Deadline extends Task {
    private final String endDate;
    private final LocalDate date;

    private final static String taskType = "D";

    private static LocalDate convertToDate(String endDate) {
        return LocalDate.parse(endDate);
    }

    /**
     * Constructor for Deadline
     * @param name
     * @param endDate
     * @param isDone
     */
    public Deadline(String name, String endDate, boolean isDone) {
        super(name, isDone);
        this.endDate = endDate;
        this.date = convertToDate(endDate);
    }

    /**
     * Constructor for Deadline where isDone without isDone parameter
     * @param name
     * @param endDate
     */
    public Deadline(String name, String endDate) {
        super(name);
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
     * Returns a string representing the deadline that can be stored in the hard drive.
     *
     * @return String representation of deadline for storage.
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
