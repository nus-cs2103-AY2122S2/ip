package duke;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    // attributes
    protected String type;

    // constructor
    public Deadline(String description, String by) {
        super(description, by);
        this.type = "D";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}