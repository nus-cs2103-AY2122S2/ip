import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(int rank, String description, LocalDate deadline) throws DateTimeParseException {
        super(rank, description);
        this.deadline = deadline;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        return "[D]" + super.toString() + " (by: " + formatter.format(this.deadline) + ")";
    }
}