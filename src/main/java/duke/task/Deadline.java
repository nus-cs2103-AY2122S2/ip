package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.CommandParser;

/**
 * Encapsulates behavior of Deadline type of Task
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * Instantiates Deadline object with date string
     *
     * @param name name for Deadline
     * @param date date of duke.task.Deadline (in d/M/yyyy format)
     * @throws DateTimeParseException date format error
     */
    public Deadline(String name, String date) throws DateTimeParseException {
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
    public Deadline(String name, String date, Boolean done) throws DateTimeParseException {
        this(name, LocalDate.parse(date, CommandParser.DATE_FORMAT), done);
    }

    /**
     * Instantiates Deadline object
     *
     * @param name name for Deadline
     * @param date date of Deadline
     */
    public Deadline(String name, LocalDate date) {
        this(name, date, false);
    }

    /**
     * Instantiates Deadline object with status
     *
     * @param name name for Deadline
     * @param date date of Deadline
     * @param done status of completion
     */
    public Deadline(String name, LocalDate date, Boolean done) {
        super(name, 'D', done);
        this.date = date;
    }

    /**
     * Returns the tag, status, name, and date of the Deadline, formatted.
     *
     * @return formatted string of the Deadline info
     */
    @Override
    public String nameWithStatus() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy");

        return String.format("%s (by: %s)",
                super.nameWithStatus(),
                this.date.format(fmt));
    }

    /**
     * Parses a formatted string from file storage, then returns the Deadline object
     *
     * @return Deadline object represented by the string
     */
    @Override
    public String fileSaveFormat() {
        return String.format("%s||%s",
                super.fileSaveFormat(),
                this.date.format(CommandParser.DATE_FORMAT));
    }
}
