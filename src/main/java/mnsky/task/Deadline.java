package mnsky.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String by;
    private LocalDate byDate;
    private LocalTime byTime;

    /**
     * Creates a deadline object.
     * @param taskName The name of the deadline.
     * @param by When the deadline is due.
     * @param byDate The date the deadline is due, can be null if it doesn't exist.
     * @param byTime The time the deadline is due, can be null if it doesn't exist.
     */
    public Deadline(String taskName, String by, LocalDate byDate, LocalTime byTime) {
        super(taskName);
        this.by = by;
        this.byDate = byDate;
        this.byTime = byTime;
    }

    /**
     * Overrides the string representation of the task to the [D] and then the generic task name.
     * Uses the date and time representation of the by parameter if it exists.
     * @return The overriden string representation of the task.
     */
    @Override
    public String toString() {
        String genericTaskname = super.getGenericTaskName();
        String dateString = "";
        String timeString = "";
        boolean dateExists = byDate != null;
        boolean timeExists = byTime != null;

        if (dateExists) {
            dateString = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

        if (timeExists) {
            timeString = byTime.format(DateTimeFormatter.ofPattern("h:mm a"));
        }

        if (dateExists && timeExists) {
            return String.format("[D]%s (by: %s, %s)", genericTaskname, dateString, timeString);
        } else if (dateExists) {
            return String.format("[D]%s (by: %s)", genericTaskname, dateString);
        } else if (timeExists) {
            return String.format("[D]%s (by: %s)", genericTaskname, timeString);
        } else {
            return String.format("[D]%s (by: %s)", genericTaskname, by);
        }
    }

    /**
     * Returns the string representation to be used for the storage data.
     * @return The string representation to be used for the storage data.
     */
    @Override
    public String getStorageData() {
        return String.format("[D]%s /by %s",  super.getGenericTaskName(), by);
    }
}
