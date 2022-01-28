package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

public class Deadline extends Task {

    protected LocalDate by;
    
    public Deadline(String description, String by, boolean done) throws DukeException {
        super(description, done);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date was incorrectly formatted! Please format it as yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toStringSaveData() {
        return String.join(" | ", "D", String.valueOf(done ? 1 : 0), description, this.by.toString());
    }

}
