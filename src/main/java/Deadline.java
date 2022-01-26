import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    protected LocalDateTime ldt;
    public String by;

    public Deadline(String description, String by) {
        super(description);
        this.ldt = parseString(by);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
        return "[D]" + super.toString() + " (by: " + ldt.format(formatter) + ")";
    }

    public LocalDateTime parseString(String by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(by, formatter);

    }

    public String storageString() {
        return String.format("[D][%s] %s %s", this.getStatusIcon(), this.description, this.by);
    }
}