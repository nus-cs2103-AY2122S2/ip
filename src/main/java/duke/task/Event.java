package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.CommandParser;

public class Event extends Task {
    private LocalDate date;

    public Event(String name, String date) throws DateTimeParseException {
        this(name, LocalDate.parse(date, CommandParser.DATE_FORMAT));
    }

    public Event(String name, String date, Boolean done) throws DateTimeParseException {
        this(name, LocalDate.parse(date, CommandParser.DATE_FORMAT), done);
    }

    public Event(String name, LocalDate date) {
        this(name, date, false);
    }

    public Event(String name, LocalDate date, Boolean done) {
        super(name, 'E', done);
        this.date = date;
    }

    @Override
    public String nameWithStatus() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy");

        return String.format("%s (at: %s)",
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
