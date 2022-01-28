package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

public class Event extends Task {

    protected LocalDate at;  
    
    public Event(String description, String at, boolean done) throws DukeException {
        super(description, done);
        try {
            this.at = LocalDate.parse(at); 
        } catch (DateTimeParseException e) {
            throw new DukeException("Date was incorrectly formatted! Please format it as yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toStringSaveData() {
        return String.join(" | ", "E", String.valueOf(done ? 1 : 0), description, this.at.toString());
    }

}
