package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected LocalDate parsedBy;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            parsedBy = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            parsedBy = null;
        }
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        if (parsedBy != null) {
            String date =  parsedBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[D]" + "[" + this.getStatusIcon() + "] " + description + " (by: " + date + ")";
        } else {
            return "[D]" + "[" + this.getStatusIcon() + "] " + description + " (by: " + by + ")";
        }
    }
}