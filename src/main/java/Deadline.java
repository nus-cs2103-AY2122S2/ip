import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private final LocalDateTime dueBy;
    private static final String DEFAULT_DATE_FORMAT = "d/MM/yyyy HHmm";
    private static final String OUTPUT_DATE_FORMAT = "dd MMMM yyyy HHmm";

    public Deadline(String title, String dueBy) throws DukeException {
        super(title);
        this.dueBy = parseDateTime(dueBy);
    }

    public LocalDateTime parseDateTime(String dueBy) throws DukeException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
            return LocalDateTime.parse(dueBy, format);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date time format." + e.getMessage());
        }
    }

    @Override
    public String getSaveFormat() {
        return String.format("%s | %s", super.getSaveFormat(), dueBy);
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
        return String.format("[D] %s (by: %s)",super.toString(), this.dueBy.format(format));
    }
}
