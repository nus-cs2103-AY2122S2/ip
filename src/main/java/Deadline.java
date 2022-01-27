import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Deadline extends Task{

    protected LocalDateTime by;

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = DateUtil.stringToDate(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateUtil.dateToString(by) + ")";
    }
}
