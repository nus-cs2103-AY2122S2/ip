package duke;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    // attributes
    protected String type;

    // constructor
    public Event(String description, String at) {
        super(description, at);
        this.type = "E";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + super.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
