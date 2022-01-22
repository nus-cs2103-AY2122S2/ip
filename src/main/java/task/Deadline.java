package task;

import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate time;

    public Deadline (String description, boolean done, LocalDate time) {
        super(description, done);
        this.time = time;
    }

    public String fileFormat() {
        return String.format("D | %s | %s | %s", this.done ? "X" : " ", this.description, this.time);
    }

    public String toString() {
        return String.format("[D][%s] %s (by %s)", this.done ? "X" : " ", this.description, this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}