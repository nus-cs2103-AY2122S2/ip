import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String name, String deadline) throws DateTimeParseException {
        this(name, LocalDate.parse(deadline, CommandParser.DATE_FORMAT));
    }

    public Deadline(String name, String deadline, Boolean done) throws DateTimeParseException {
        this(name, LocalDate.parse(deadline, CommandParser.DATE_FORMAT), done);
    }

    public Deadline(String name, LocalDate date) {
        this(name, date, false);
    }

    public Deadline(String name, LocalDate date, Boolean done) {
        super(name, 'D', done);
        this.date = date;
    }

    @Override
    public String nameWithStatus() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy");

        return String.format("%s (by: %s)",
                super.nameWithStatus(),
                this.date.format(fmt));
    }

    @Override
    public String fileSaveFormat() {
        return String.format("%s||%s",
                super.fileSaveFormat(),
                this.date.format(CommandParser.DATE_FORMAT));
    }
}
