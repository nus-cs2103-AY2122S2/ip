package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.CommandParser;

public class Deadline extends Task {
    private LocalDate date;

    /**
     * Instantiate duke.task.Deadline object with date string
     *
     * @param name name for duke.task.Deadline
     * @param date date of duke.task.Deadline (in d/M/yyyy format)
     * @throws DateTimeParseException date format error
     */
    public Deadline(String name, String date) throws DateTimeParseException {
        this(name, LocalDate.parse(date, CommandParser.DATE_FORMAT));
    }

    /**
     * Instantiate duke.task.Deadline object with date string, and status
     *
     * @param name name for duke.task.Deadline
     * @param date date of duke.task.Deadline (in d/M/yyyy format)
     * @param done status of completion
     * @throws DateTimeParseException date format error
     */
    public Deadline(String name, String date, Boolean done) throws DateTimeParseException {
        this(name, LocalDate.parse(date, CommandParser.DATE_FORMAT), done);
    }

    /**
     * Instantiate duke.task.Deadline object
     *
     * @param name name for duke.task.Deadline
     * @param date date of duke.task.Deadline
     */
    public Deadline(String name, LocalDate date) {
        this(name, date, false);
    }

    /**
     * Instantiate duke.task.Deadline object with status
     *
     * @param name name for duke.task.Deadline
     * @param date date of duke.task.Deadline
     * @param done status of completion
     */
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
