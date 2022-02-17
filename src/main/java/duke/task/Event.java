package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate dateTime;
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.dateTime = LocalDate.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String infoString() {
        return "D/" + super.infoString() + "/" + at;
    }
}
