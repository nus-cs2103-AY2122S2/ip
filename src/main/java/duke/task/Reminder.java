package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * A class to represent Reminders for Tasks.
 */
public class Reminder {
    protected LocalDateTime reminderTime;

    /**
     * Creates a new Reminder instance.
     *
     * @param reminderTime The time at which to remind the user.
     */
    public Reminder(LocalDateTime reminderTime) {
        this.reminderTime = reminderTime;
    }

    /**
     * Returns a String representation of the Reminder date and time in a specific format.
     *
     * @return A String representation of the Reminder date and time.
     */
    public String getDateTime() {
        return reminderTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
    }

    /**
     * Returns the LocalDateTime object this Reminder encapsulates.
     *
     * @return The LocalDateTime object.
     */
    public LocalDateTime getLocalDateTime() {
        return this.reminderTime;
    }
}
