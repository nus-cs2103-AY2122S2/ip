import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;
    protected String time;

    public Deadline(String description, LocalDate by, String time) {
        super(description);
        this.by = by;
        this.time = time;
    }

    @Override
    public String toString() {
     return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +" " + time + ")";
    }
}
