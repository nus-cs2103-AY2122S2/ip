import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    private final LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    private String byMmmDdYyyyFormat() {
        return String.format("%s %s %s", by.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                by.getDayOfMonth(), by.getYear());
    }

    public Deadline(String isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
    }

    @Override
    public String fileFormat() {
        return String.format("D | %s | %s\n", super.fileFormat(), by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), byMmmDdYyyyFormat());
    }
}