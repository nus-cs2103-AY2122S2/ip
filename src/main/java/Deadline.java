import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    // attributes
<<<<<<< HEAD
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
=======
    protected LocalDate by;

    // constructor
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
>>>>>>> branch-Level-8
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return "[D]" + super.toString() + " (by:" + super.by + ")";
=======
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
>>>>>>> branch-Level-8
    }
}