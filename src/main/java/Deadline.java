import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;

    public Deadline(String name, String by) {
        super(name);
        System.out.println(by);
        try {
            LocalDate d1 = LocalDate.parse(by);
            this.by = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException ex) {
            this.by = by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toText() {
        return "D | " + (this.getDone() ? 1 : 0) + " | " + this.getName() + " | " + this.by + "\n";
    }
}
