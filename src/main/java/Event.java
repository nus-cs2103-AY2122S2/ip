import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    // attribute
<<<<<<< HEAD
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
    public String getBy() {
        return super.by;
=======
    protected LocalDate at;

    // constructor
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
>>>>>>> branch-Level-8
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return "[E]" + super.toString() + " (at:" + super.by + ")";
=======
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
>>>>>>> branch-Level-8
    }
}
