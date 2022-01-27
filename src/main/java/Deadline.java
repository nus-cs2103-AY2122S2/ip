import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String save() {
        return "D | " + super.save() + " | " + this.by  + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}
