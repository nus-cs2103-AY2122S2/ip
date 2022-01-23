import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);

        if (by == null) {
            throw new DukeException("The deadline of a Deadline must be specified");
        }

        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy h:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
