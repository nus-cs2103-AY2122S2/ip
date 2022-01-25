import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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
    public String getBy() {
        return super.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + super.by + ")";
    }
}