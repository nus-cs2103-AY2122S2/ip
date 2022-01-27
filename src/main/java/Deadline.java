import java.time.LocalDate;

public class Deadline extends Task {
    protected String by;
    protected Date date;

    public Deadline(String description, String by) {
        super(description);
        this.date = new Date(by);
        this.by = by;
    }

    @Override
    public String toMemoryString() {
        return "D" + super.toMemoryString() + "@" + this.by;
    }

    @Override
    public boolean isOn(Date date) {
        return this.date.equals(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.formattedTime() + ")";
    }
}
