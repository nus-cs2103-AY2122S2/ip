import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
    protected DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;

    public Deadline(String taskName, LocalDate date) {
        super(taskName);
        this.deadlineDate = date;
    }

    public Deadline(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(taskName);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String getTaskIcon() {
        return "D";
    }

    @Override
    public String toDataString() {
        String isDone = super.isMarked() ? "1" : "0";
        if (deadlineTime != null) {
            return getTaskIcon() + " | " + isDone + " | " + taskName 
                    + " | " + deadlineDate + " | " + deadlineTime;
        } else {
            return getTaskIcon() + " | " + isDone + " | " + taskName + " | " + deadlineDate;
        }
    }

    @Override
    public String toString() {
        if (deadlineTime != null) {
            return "[" + getTaskIcon() + "][" + super.getStatusIcon() + "] " +
                    super.taskName + " (by: " + dateFormatter.format(deadlineDate)
                    + " " + timeFormatter.format(deadlineTime) + ")";
        } else {
            return "[" + getTaskIcon() + "][" + super.getStatusIcon() + "] " +
                    super.taskName + " (by: " + dateFormatter.format(deadlineDate) + ")";
        }
    }
}
