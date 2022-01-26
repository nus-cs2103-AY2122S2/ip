import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;
    private static final char DEADLINE_SYMBOL = 'D';

    public Deadline() {
        super();

        this.deadlineDate = LocalDate.now(); // just get default date
    }

    public Deadline(boolean isDone, String taskDescription, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(isDone, taskDescription);

        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String saveFileFormat() {
        return DEADLINE_SYMBOL + "|" + this.isDone + "|" + taskDescription + "|"
                + this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + "|"
                + this.deadlineTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
                + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " "
                + this.deadlineTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
                + ")";
    }
}
