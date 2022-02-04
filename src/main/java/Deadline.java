import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate by;
    public Deadline(String task, LocalDate by) {
        super(task.trim());
        this.by = by;
    }

    public Deadline(String task, LocalDate by, boolean done) {
        super(task, done);
        this.by = by;
    }

    @Override
    public Deadline mark() {
        return new Deadline(task, by, true);
    }

    @Override
    public Deadline unmark() {
        return new Deadline(task, by, false);
    }

    @Override
    public String saveData() {
        int done = super.done? 1 : 0;
        return Type.D + " | " + done + " | " + task + " | " + by;
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
