import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime byTime;

    public Deadline(String description, LocalDateTime byTime) {
        super(description);
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        String byTimeFormatted = this.byTime.format(DateTimeFormatter.ofPattern("HH:mm, MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + byTimeFormatted + ")";
    }
}
