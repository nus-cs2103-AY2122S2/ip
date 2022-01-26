import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate byDate;
    protected LocalTime byTime;

    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    protected String getDateTime() {
        return byDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " "
                + byTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public String getType() {
        return  "D";
    }

    @Override
    public String formatToSave() {
        return super.formatToSave() + "|" + this.by;
    };

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateTime() + ")";
    }
}