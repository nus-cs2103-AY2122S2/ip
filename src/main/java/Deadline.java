import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String[] details) {
        this.description = details[0];
        this.by = LocalDateTime.parse(details[1], (DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) + ")";
    }
}
