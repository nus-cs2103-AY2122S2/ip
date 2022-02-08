package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/** A class to represent reminders for tasks. */
public class Reminder {
    protected LocalDateTime reminderTime;

    /**
     * Creates a new Reminder instance.
     *
     * @param reminderTime The time to remind the user.
     */
    public Reminder(LocalDateTime reminderTime) {
        this.reminderTime = reminderTime;
    }

    /**
     * Returns a string representation of the reminder date and time in a specific format.
     *
     * @return A string representation of the reminder date and time.
     */
    public String getDateTime() {
        return reminderTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
    }
}
