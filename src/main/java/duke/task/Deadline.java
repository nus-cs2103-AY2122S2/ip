package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline which is a subclass of Task
 * Includes a dueDate attribute. Overrides toString() from Task
 */
public class Deadline extends Task {
    private LocalDate dueDate;
    private LocalTime dueTime;

    /**
     * Constructor for Deadline with date
     *
     * @param name Name of Deadline
     * @param date Date of deadline in yyyy-mm-dd format
     * @throws DateTimeParseException If date is not in yyyy-mm-dd format
     */
    public Deadline(String name, String date) throws DateTimeParseException {
        super(name);
        this.dueDate = LocalDate.parse(date);
        this.dueTime = null;
    }

    /**
     * Constructor for Deadline with date and time
     *
     * @param name Name of Deadline
     * @param date Date of deadline in yyyy-mm-dd format
     * @param time Time of deadline in hh:mm format
     * @throws DateTimeParseException If date is not in yyyy-mm-dd format AND/OR time is not in hh:mm format
     */
    public Deadline(String name, String date, String time) throws DateTimeParseException {
        super(name);
        this.dueDate = LocalDate.parse(date);
        this.dueTime = LocalTime.parse(time);
    }

    /**
     * Returns the dueDate of this Deadline object
     *
     * @return LocalDate object of dueDate
     */
    public LocalDate getDueDate() {
        return this.dueDate;
    }

    /**
     * Returns the dueTime of this Deadline object
     *
     * @return LocalTime object of dueTime
     */
    public LocalTime getDueTime() {
        return this.dueTime;
    }

    /**
     * Changes dueDate to new LocalDate
     *
     * @param date LocalDate object of new date
     */
    public void changeDueDate(LocalDate date) {
        this.dueDate = date;
    }

    /**
     * Changes dueTime to new LocalTime
     *
     * @param time LocalTIme object of new time
     */
    public void changeDueTime(LocalTime time) {
        this.dueTime = time;
    }

    /**
     * Returns String representation of Deadline
     *
     * @return String of Deadline task, eg [D][0] Deadline (by: 22 Feb 2022 2:22pm) vs [D][1] Deadline (by: 22 Feb 2022 2:22pm)
     */
    @Override
    public String toString() {
        String dueDateAndTime = (this.dueTime == null)
                ? dateConverterToString(this.dueDate)
                : dateConverterToString(this.dueDate) + " " + timeConverterToString(this.dueTime);
        return "[D]" + super.toString() + " (by: " + dueDateAndTime + ")";
    }
}