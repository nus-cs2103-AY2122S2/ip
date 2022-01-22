import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final String endDate;

    public Deadline(String name, String endDate) {
        super(name);
        this.endDate = endDate;
    }

    public Deadline(String name, String endDate, boolean isDone) {
        super(name, isDone);
        this.endDate = endDate;
    }

    public Deadline markAsDone() {
        return new Deadline(this.name, this.endDate, true);
    }

    public Deadline markAsUndone() {
        return new Deadline(this.name, this.endDate, false);
    }

    @Override
    public String toString() {
        String taskType = "D";
        String doneMark;
        if (super.isDone) {
            doneMark = "X";
        } else {
            doneMark = " ";
        }
        return String.format("[%s][%s] %s (by: %s)",
                taskType, doneMark, super.name, endDate);
    }
}
