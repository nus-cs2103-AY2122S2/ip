package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Deadline extends Task {

    protected String by;
    private LocalDateTime dateTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.dateTime = LocalDateTime.parse(this.by,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));   //if fail to parse, throw exception
    }

    public Deadline(String description, String by, boolean isDone) {    //constructor with marked status
        super(description, isDone);
        this.by = by;
        this.dateTime = LocalDateTime.parse(this.by,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));   //if fail to parse, throw exception
    }

    public String reFormatDateTime() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    @Override
    public String getTaskData() {
        return super.getTaskData() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.reFormatDateTime() + ")";
    }
}
