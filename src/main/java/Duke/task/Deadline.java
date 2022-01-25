package Duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Deadline extends Task implements Serializable {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public LocalDate getDate() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.getDescription() +
                "by (" + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}