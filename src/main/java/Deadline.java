import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate deadline;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    private String dateToString() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", dateToString());
    }
}
