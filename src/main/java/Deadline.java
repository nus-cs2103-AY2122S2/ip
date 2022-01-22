import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final String endDate;
    private final LocalDate date;

    private static final String taskType = "D";

    private static LocalDate convertToDate(String endDate) {
        return LocalDate.parse(endDate);
    }

    public Deadline(String name, String endDate) {
        super(name);
        this.endDate = endDate;
        this.date = convertToDate(endDate);
    }

    public Deadline(String name, String endDate, boolean isDone) {
        super(name, isDone);
        this.endDate = endDate;
        this.date = convertToDate(endDate);
    }

    public Deadline markAsDone() {
        return new Deadline(this.name, this.endDate, true);
    }

    public Deadline markAsUndone() {
        return new Deadline(this.name, this.endDate, false);
    }

    public String convertToStoredListFormat() {
        String doneIndicator = "false";
        if (super.isDone) {
            doneIndicator = "true";
        }
        String storedListFormat = String.format("%s|%s|%s|%s", taskType, doneIndicator, super.name, this.date);
        return storedListFormat;
    }

    @Override
    public String toString() {
        String doneMark;
        if (super.isDone) {
            doneMark = "X";
        } else {
            doneMark = " ";
        }
        String dateOutput = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[%s][%s] %s (by: %s)",
                taskType, doneMark, super.name, dateOutput);
    }
}
