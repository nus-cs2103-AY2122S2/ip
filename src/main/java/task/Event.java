package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate time;

    /** Creates new Event Task. */
    public Event (String description, boolean isDone, Integer priorityLevel, LocalDate time) {
        super(description, isDone, priorityLevel);
        this.time = time;
    }

    @Override
    public String fileFormat() {
        String priorityString = getPriorityLevel() == null ? "" : "| P" + getPriorityLevel();
        return String.format("E | %s | %s | %s %s", getTaskStatus() ? "X" : " ", getDescription(),
                this.time, priorityString);
    }

    @Override
    public String toString() {
        String priorityString = getPriorityLevel() == null ? "" : " (Priority " + getPriorityLevel() + ") ";
        return String.format("[E][%s]%s %s (at %s)", getTaskStatus() ? "X" : " ", priorityString, getDescription(),
                this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
