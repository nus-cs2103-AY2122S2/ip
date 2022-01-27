import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    @Override
    public String save() {
        String dateTime = by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return "D | " + super.save() + " | " + dateTime  + System.lineSeparator();
    }

    @Override
    public String toString() {
        String dateTime = by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }
}
