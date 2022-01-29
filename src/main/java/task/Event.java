package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate time;

    /** Creates new Event Task. */
    public Event (String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    public String fileFormat() {
        return String.format("E | %s | %s | %s", getTaskStatus() ? "X" : " ", getDescription(), this.time);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at %s)", getTaskStatus() ? "X" : " ", getDescription(),
                this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
