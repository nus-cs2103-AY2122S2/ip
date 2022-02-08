package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a Deadline.
 */
public class Deadline extends Task {

    private LocalDateTime date;
    private String dateString = "";

    /**
     * Constructor for Deadline class. Takes in a LocalDateTime object as the date instead of a string.
     * @param taskName The string that represents the name of this deadline.
     * @param date The LocalDateTime object that represents its deadline.
     */
    public Deadline(String taskName, LocalDateTime date) {
        this.taskName = taskName;
        this.date = date;
    }
    /**
     * Constructor for Deadline class. Takes in a string describing its deadline.
     * @param taskName The string that represents the name of this deadline.
     * @param dateString The string that represents its deadline.
     */
    public Deadline(String taskName, String dateString) {
        this.taskName = taskName;
        this.dateString = dateString;
    }

    public char getType() {
        return 'D';
    }

    /**
     * Returns a String representation of the deadline Task, to be displayed.
     *
     * @return The String representation of the deadline task, in the form of:<br>
     * [&lt;Type&gt;][&lt;Marked&gt;]  &lt;Task Name&gt; (by: &lt;Deadline&gt;)
     */
    @Override
    public String toString() {
        return String.format("[%c][%c] %s (by: %s)", this.getType(), this.done, this.taskName, this.getDate());
    }

    /**
     * Gets the LocalDateTime object associated to this deadline task, if it exists.
     * Returns null if it does not exist.
     *
     * @return The LocalDateTime object containing the Date of the deadline, null if it does not exist.
     * @see Deadline#getDate()
     */
    @Override
    public LocalDateTime getDateObj() {
        return this.date;
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
     * Returns whether if the given object is equals to this deadline task.
     * The given object will be equals to this object if and only if it is of the same task type,
     * has the same name and has the same deadline.
     *
     * @param o The object to be compared to.
     * @return true if they are equivalent.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deadline)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        Deadline deadline = (Deadline) o;

        if (deadline.taskName.equals(this.taskName)) {
            return deadline.getDate().equals(this.getDate());
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
