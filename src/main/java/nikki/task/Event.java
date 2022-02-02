package nikki.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import nikki.command.CommandParser;

/**
 * Encapsulates behavior of Event type of Task
 */
public class Event extends Task {
    private LocalDate date;

    /**
     * Instantiates Deadline object with date string
     *
     * @param name name for Deadline
     * @param date date of duke.task.Deadline (in d/M/yyyy format)
     * @throws DateTimeParseException date format error
     */
    public Event(String name, String date) throws DateTimeParseException {
        this(name, LocalDate.parse(date, CommandParser.DATE_FORMAT));
    }

    /**
     * Instantiates Deadline object with date string, and status
     *
     * @param name name for Deadline
     * @param date date of Deadline (in d/M/yyyy format)
     * @param done status of completion
     * @throws DateTimeParseException date format error
     */
    public Event(String name, String date, Boolean done) throws DateTimeParseException {
        this(name, LocalDate.parse(date, CommandParser.DATE_FORMAT), done);
    }

    /**
     * Instantiates Deadline object
     *
     * @param name name for Deadline
     * @param date date of Deadline
     */
    public Event(String name, LocalDate date) {
        this(name, date, false);
    }

    /**
     * Instantiates Deadline object with status
     *
     * @param name name for Deadline
     * @param date date of Deadline
     * @param done status of completion
     */
    public Event(String name, LocalDate date, Boolean done) {
        super(name, 'E', done);
        this.date = date;
    }

    /**
     * Returns the tag, status, name, and date of the Event, formatted.
     *
     * @return formatted string of the Event info
     */
    @Override
    public String nameWithStatus() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy");

        return String.format("%s (at: %s)",
                super.nameWithStatus(),
                this.date.format(fmt));
    }

    /**
     * Parses a formatted string from file storage, then returns the Event object
     *
     * @return Event object represented by the string
     */
    @Override
    public String toFileSaveFormat() {
        return String.format("%s||%s",
                super.toFileSaveFormat(),
                this.date.format(CommandParser.DATE_FORMAT));
    }
}
