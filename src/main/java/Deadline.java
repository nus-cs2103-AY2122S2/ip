import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime parsedDate = LocalDateTime.parse(dueDate, formatter);
        this.dueDate = parsedDate;
    }

    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmma")) + ")";
    }
}
