import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(boolean isDone, String description, String by) throws DateTimeParseException {
        super(description);
        this.by = DateUtil.stringToDate(by);
        this.isDone = isDone;
    }

    public Deadline(String description, String by) throws DateTimeParseException {
        this(false, description, by);
    }

    @Override
    public String appendtoFile() {
        return "D|" + (super.isDone ? "1" : "0") + "|" + super.description + "|" + DateUtil.dateToString(by) + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateUtil.dateToString(by) + ")";
    }
}
