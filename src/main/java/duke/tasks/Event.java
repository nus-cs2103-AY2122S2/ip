package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Task with a Date.
 */
public class Event extends Task {

    private LocalDateTime date = null;
    private String dateString = "";

    public Event(String taskName, LocalDateTime date) {
        this.taskName = taskName;
        this.date = date;
    }

    public Event(String taskName, String dateString) {
        this.taskName = taskName;
        this.dateString = dateString;
    }


    public char getType() {
        return 'E';
    }

    /**
     * Returns a String representation of the event, in the form to be saved,
     * such that it can be properly parsed when loaded back from the storage.
     *
     * @return The string representation of the event in the format to be saved.
     */
    @Override
    public String getDateForSaving() {
        if (this.date == null) {
            return String.format("%c\t%c\t%s\t%s\n", getType(), getDone(), getTaskName(), this.dateString);
        } else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return String.format("%c\t%c\t%s\t%s\n", getType(), getDone(), getTaskName(), this.date.format(format));
        }
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
        if (! (o instanceof Event)) {
            return false;
        }

        @SuppressWarnings("Unchecked")
        Event event = (Event) o;

        if (event.taskName.equals(this.taskName)) {
            return event.getDate().equals(this.getDate());
        }

        return false;
    }
}
