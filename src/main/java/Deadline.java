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

    Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = LocalDate.parse(deadline);
    }

    String getTaskType() {
        return "D";
    }

    public String toString() {
        return String.format("[%s]", getTaskType())
                + super.toString()
                + String.format(" (by: %s)", dateToString());
    }
}
