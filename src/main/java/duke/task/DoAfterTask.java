package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DoAfterTask extends Task {
    private final LocalDate after;

    public DoAfterTask(String description, String after) {
        super(description);
        after = after.trim();
        this.after = LocalDate.parse(after);
    }

    public DoAfterTask(String description, LocalDate after, boolean done) {
        super(description, done);
        this.after = after;
    }

    public LocalDate getAfter() {
        return this.after;
    }

    @Override
    public String toString() {
        return "[A]" + super.toString() + " (after: " + after.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}