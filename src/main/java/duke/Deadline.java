package duke;
import java.time.LocalDate;
public class Deadline extends Task {

    protected String by;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
        // if(Can get time, set this.by to with time)
        this.by = date.getDayOfMonth() + " " + date.getMonth() + " " + date.getYear();
    }

    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}