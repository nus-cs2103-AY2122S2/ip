import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate deadline;
    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)",
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
