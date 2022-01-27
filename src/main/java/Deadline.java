import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter outputFormatter =
            DateTimeFormatter.ofPattern("MMM d yyyy hh:mma");
    private final LocalDateTime dueDate;

    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String getIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", dueDate.format(outputFormatter));
    }
}
