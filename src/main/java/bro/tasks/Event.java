package bro.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Task with a Date.
 */
public class Event extends Task {

    private LocalDateTime date = null;
    private String dateString = "";

    /**
     * Constructor for Event class. Takes in a LocalDateTime object as the date instead of a string.
     * @param taskName The string that represents the name of this event.
     * @param date The LocalDateTime object that represents the date of this event.
     */
    public Event(String taskName, LocalDateTime date) {
        this.taskName = taskName;
        this.date = date;
    }

    /**
     * Constructor for Event class. Takes in a string describing the date of this event.
     * @param taskName The string that represents the name of this event.
     * @param dateString The string that represents the date of this event.
     */
    public Event(String taskName, String dateString) {
        this.taskName = taskName;
        this.dateString = dateString;
    }

    public char getType() {
        return 'E';
    }

    /**
     * Gets the LocalDateTime object associated to this event, if it exists.
     * Returns null if it does not exist.
     *
     * @return The LocalDateTime object containing the Date of the event, null if it does not exist.
     * @see Event#getDate()
     */
    @Override
    public LocalDateTime getDateObj() {
        return this.date;
    }

    /**
     * Returns a String representation of the event, to be displayed.
     *
     * @return The String representation of the event, in the form of:<br>
     * [&lt;Type&gt;][&lt;Marked&gt;]  &lt;Task Name&gt; (at: &lt;Date&gt;)
     */
    @Override
    public String toString() {
        return String.format("[%c][%c] %s (at: %s)", this.getType(), this.done, this.taskName, this.getDate());
    }

    /**
     * Gets the String representation of the deadline.
     * If the date is stored as a LocalDateTime object, this will format as (D MMM YYYY H:MM AM/PM).
     *
     * @return The String representation of the deadline.
     */
    @Override
    public String getDate() {
        if (this.date == null) {
            return this.dateString;
        } else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMM yyyy, K:mma");
            return this.date.format(format);
        }
    }

    /**
     * Returns whether if the given object is equals to this event.
     * The given object will be equals to this object if and only if it is of the same task type,
     * has the same name and has the same date.
     *
     * @param o The object to be compared to.
     * @return true if they are equivalent.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Event)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        Event event = (Event) o;

        if (event.taskName.equals(this.taskName)) {
            return event.getDate().equals(this.getDate());
        }

        return false;
    }

    /**
     * Updates the current date information with the given new date.
     *
     * @param newDate The new date to replace the current date.
     * @return True if updated successfully, False if the new date given is the same as the current date.
     */
    @Override
    public boolean updateDate(LocalDateTime newDate) {

        if (newDate.equals(this.date)) {
            return false;
        }

        this.date = newDate;
        this.dateString = "";
        return true;
    }

    /**
     * Updates the current date information with the given new date.
     *
     * @param newDate The new date to replace the current date.
     * @return True if updated successfully, False if the new date given is the same as the current date or empty.
     */
    @Override
    public boolean updateDate(String newDate) {

        if (newDate.strip().equals(this.dateString)) {
            return false;
        }

        this.date = null;
        this.dateString = newDate.strip();

        return true;
    }
}
