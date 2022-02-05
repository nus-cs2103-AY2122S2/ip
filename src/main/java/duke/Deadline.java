package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String taskName, String by) {
        super(taskName);
        DateTimeFormatter format = DateTimeFormatter.ofPattern(" yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by, format);
    }

    @Override
    public String toString() {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
        return "[D]" + super.toString() + "(by:" + by.format(displayFormat) + ")";
    }
}
