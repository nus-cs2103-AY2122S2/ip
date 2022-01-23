package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate time;

    public Deadline (String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    public String fileFormat() {
        return String.format("D | %s | %s | %s", this.isDone ? "X" : " ", this.description, this.time);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by %s)", this.isDone ? "X" : " ", this.description, this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}