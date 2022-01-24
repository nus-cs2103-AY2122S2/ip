import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate dateRange;

    public Event(String name, String dateRange) throws DateTimeParseException {
        this(name, LocalDate.parse(dateRange, CommandParser.DATE_FORMAT));
    }

    public Event(String name, String dateRange, Boolean done) throws DateTimeParseException {
        this(name, LocalDate.parse(dateRange, CommandParser.DATE_FORMAT), done);
    }

    public Event(String name, LocalDate dateRange) {
        this(name, dateRange, false);
    }

    public Event(String name, LocalDate dateRange, Boolean done) {
        super(name, done);
        this.dateRange = dateRange;
    }

    @Override
    public String nameWithStatus() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy");

        return String.format("[E]%s (at: %s)",
                super.nameWithStatus(),
                this.dateRange.format(fmt));
    }

}
