import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    private final LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public Deadline(String isDone, String description, String by) {
        super(isDone, description);
        this.by = LocalDate.parse(by);
    }

    private String byMmmDdYyyyFormat() {
        return String.format("%s %s %s", by.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                by.getDayOfMonth(), by.getYear());
    }

    @Override
    public String toFileFormat() {
        return String.format("D | %s | %s\n", super.toFileFormat(), by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), byMmmDdYyyyFormat());
    }
}
