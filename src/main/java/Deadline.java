import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline() {
        super();

        this.deadline = LocalDate.now(); // just get default date
    }

    public Deadline(String taskDescription, LocalDate eventTime) {
        super(taskDescription);

        this.deadline = eventTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
