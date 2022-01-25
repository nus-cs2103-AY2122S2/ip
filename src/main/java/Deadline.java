import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;
    private LocalTime deadlineTime;

    public Deadline() {
        super();

        this.deadline = LocalDate.now(); // just get default date
    }

    public Deadline(String taskDescription, LocalDate eventTime) {
        super(taskDescription);

        this.deadline = eventTime;
    }

    public Deadline(String taskDescription, LocalDate eventTime, LocalTime deadlineTime) {
        super(taskDescription);

        this.deadline = eventTime;
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " "
                + this.deadlineTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
                + ")";
    }
}
