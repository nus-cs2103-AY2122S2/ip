package karen.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Stores the (/by) date in which the Deadline occurs by
 */
public class Deadline extends Task {
    protected LocalDate by;
    public Deadline(String description, String by) {
        super(description);
        this.by = this.parseDate(by);
    }

    @Override
    public String toSaveData() {
        return String.format("D|%s|%s|%s", this.isDone, this.getDescription(), this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
