package task;

import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDate time;

    public Event (String description, boolean done, LocalDate time) {
        super(description, done);
        this.time = time;
    }

    public String fileFormat() {
        return String.format("E | %s | %s | %s", this.done ? "X" : " ", this.description, this.time);
    }

    public String toString() {
        return String.format("[E][%s] %s (at %s)", this.done ? "X" : " ", this.description, this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}