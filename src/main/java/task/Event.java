package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDate time;

    public Event (String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    public String fileFormat() {
        return String.format("E | %s | %s | %s", this.isDone ? "X" : " ", this.description, this.time);
    }

    public String toString() {
        return String.format("[E][%s] %s (at %s)", this.isDone ? "X" : " ", this.description, this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}