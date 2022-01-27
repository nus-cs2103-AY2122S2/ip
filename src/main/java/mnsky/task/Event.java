package mnsky.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {
    private String at;
    private LocalDate atDate;
    private LocalTime atTime;

    public Event(String taskName, String at, LocalDate atDate, LocalTime atTime) {
        super(taskName);
        at = at;
        atDate = atDate;
        atTime = atTime;
    }

    @Override
    public String toString() {
        String genericTaskname = super.getGenericTaskName();

        String dateString = "";
        String timeString = "";
        boolean dateExists = atDate != null;
        boolean timeExists = atTime != null;

        if (dateExists) {
            dateString = atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

        if (timeExists) {
            timeString = atTime.format(DateTimeFormatter.ofPattern("h:mm a"));
        }

        if (dateExists && timeExists) {
            return String.format("[E]%s (at: %s, %s)", genericTaskname, dateString, timeString);
        } else if (dateExists) {
            return String.format("[E]%s (at: %s)", genericTaskname, dateString);
        } else if (timeExists) {
            return String.format("[E]%s (at: %s)", genericTaskname, timeString);
        } else {
            return String.format("[E]%s (at: %s)", genericTaskname, at);
        }
    }

    @Override
    public String getStorageData() {
        return String.format("[E]%s /at %s", super.getGenericTaskName(), at);
    }
}
