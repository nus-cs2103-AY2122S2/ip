package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime dueDate;

    public Deadline(String description, String dueDate, boolean fromDisk) {
        super(description);
        if (fromDisk) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hhmma");
            LocalDateTime parsedDate = LocalDateTime.parse(dueDate, formatter);
            this.dueDate = parsedDate;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime parsedDate = LocalDateTime.parse(dueDate, formatter);
            this.dueDate = parsedDate;
        }
    }

    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmma")) + ")";
    }
}
