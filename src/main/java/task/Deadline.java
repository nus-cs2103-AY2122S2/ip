package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDateTime by;

    public Deadline(String description, String date) {
        this.description = description;
        this.by = LocalDateTime.parse(date, (DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) + ")";
    }
}
