package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate time;

    /** Creates new Deadline Task. */
    public Deadline (String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    public String fileFormat() {
        return String.format("D | %s | %s | %s", getTaskStatus() ? "X" : " ", getDescription(), this.time);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by %s)", getTaskStatus() ? "X" : " ", getDescription(),
                this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
