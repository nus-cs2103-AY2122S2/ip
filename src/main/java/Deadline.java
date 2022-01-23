import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {

    protected LocalDate dueDate;
    protected LocalTime dueTime;

    public Deadline(String name, LocalDate dueDate, LocalTime dueTime) {
        super(name);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    public Deadline(String name, boolean isMark, String dateTime) {
        super(name, isMark);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %s)", super.toString(), dueDate, dueTime);
    }

    @Override
    public String toData() {
        return "D|" + super.toData() + "|" + dateTime;
    }
}
