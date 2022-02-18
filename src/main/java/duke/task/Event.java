package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Event which is a subclass of Task
 * Overrides toString() from Task
 */
public class Event extends Task {
    private LocalDate dueDate;
    private LocalTime dueTime;

    /**
     * Constructor for Event. Takes in name and date but no time.
     *
     * @param name name of the Event
     * @param date date in yyyy-mm-dd format only
     * @throws DateTimeParseException If date format is not in yyyy-mm-dd format
     */
    public Event (String name, String date) throws DateTimeParseException {
        super(name);
        this.dueDate = LocalDate.parse(date);
        this.dueTime = null;
    }

    /**
     * Constructor for Event. Includes name, date and time
     *
     * @param name name of the Event
     * @param date date in yyyy-mm-dd format only
     * @param time time in hh:mm format
     * @throws DateTimeParseException if date format is not in yyyy-mm-dd format AND/OR time is not in hh:mm format
     */
    public Event (String name, String date, String time) throws DateTimeParseException {
        super(name);
        this.dueDate = LocalDate.parse(date);
        this.dueTime = LocalTime.parse(time);
    }

    /**
     * Returns the LocalDate associated with the Event object
     *
     * @return LocalDate object of the dueDate
     */
    public LocalDate getDueDate() {
        return this.dueDate;
    }

    /**
     * Returns the dueTime of this Event object
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
     * Returns String representation of Event
     *
     * @return String of Event task, eg: [E][0] Event (at: 22 Feb 2022 2:22pm) vs [E][1] Event (at: 22 Feb 2022 2:22pm)
     */
    @Override
    public String toString() {
        String dueDateAndTime = (this.dueTime == null)
                ? dateConverterToString(this.dueDate)
                : dateConverterToString(this.dueDate) + " " + timeConverterToString(this.dueTime);
        return "[E]" + super.toString() + " (at: " + dueDateAndTime + ")";
    }
}