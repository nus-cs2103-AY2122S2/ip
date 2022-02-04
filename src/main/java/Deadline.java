import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate by;
    public Deadline(String task, LocalDate by) {
        super(task.trim());
        this.by = by;
    }

    public Deadline(String task, boolean done) {
        super(task, done);
    }

    @Override
    public Deadline mark() {
        return new Deadline(task, true);
    }

    @Override
    public Deadline unmark() {
        return new Deadline(task, false);
    }

    @Override
    public LocalDate getDate() {
        return by;
    }
    @Override
    public String toString() {
        return "["+Type.D+"]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
