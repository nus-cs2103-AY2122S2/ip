package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate dateTime;
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.dateTime = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String infoString() {
        return "D/" + super.infoString() + "/" + by;
    }
}