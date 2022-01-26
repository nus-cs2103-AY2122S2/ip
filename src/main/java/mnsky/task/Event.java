package mnsky.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {
    private String at;
    private LocalDate atDate;
    private LocalTime atTime;

    /**
     * Creates an event object.
     * @param taskName The name of the event.
     * @param at When the event will happen.
     * @param atDate The date the event will happen, can be null if it doesn't exist.
     * @param atTime The time the event will happen, can be null if it doesn't exist.
     */
    public Event(String taskName, String at, LocalDate atDate, LocalTime atTime) {
        super(taskName);
        this.at = at;
        this.atDate = atDate;
        this.atTime = atTime;
    }

    /**
     * Overrides the string representation of the task to the [E] and then the generic task name.
     * Uses the date and time representation of the at parameter if it exists.
     * @return The overriden string representation of the task.
     */
    @Override
    public String toString() {
        String genericTaskname = super.getGenericTaskName();

        String dateString = "";
        String timeString = "";
        boolean dateExists = this.atDate != null;
        boolean timeExists = this.atTime != null;

        if (dateExists) {
            dateString = this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

        if (timeExists) {
            timeString = this.atTime.format(DateTimeFormatter.ofPattern("h:mm a"));
        }

        if (dateExists && timeExists) {
            return String.format("[E]%s (at: %s, %s)", genericTaskname, dateString, timeString);
        } else if (dateExists) {
            return String.format("[E]%s (at: %s)", genericTaskname, dateString);
        } else if (timeExists) {
            return String.format("[E]%s (at: %s)", genericTaskname, timeString);
        } else {
            return String.format("[E]%s (at: %s)", genericTaskname, this.at);
        }
    }

    /**
     * Returns the string representation to be used for the storage data.
     * @return The string representation to be used for the storage data.
     */
    @Override
    public String getSaveData() {
        return String.format("[E]%s /at %s", super.getGenericTaskName(), at);
    }
}
