package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline task that inherits from Task
 */
public class Deadline extends Task {
    protected LocalDate by;
    protected LocalTime time;

    /**
     * Default constructor of Deadline
     *
     * @param description Description of deadline
     * @param by Time/Date of deadline
     * @throws DukeException Throws if given invalid date/time
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            String[] split = by.split(" ");
            this.by = LocalDate.parse(split[0]);
            if (split.length > 1) {
                time = LocalTime.parse(split[1]);
            } else {
                time = null;
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date");
        }
    }

    /**
     * Overloaded Deadline constructor that takes in extra isDone variable
     * to set whether deadline is done
     *
     * @param description Description of deadline
     * @param isDone Whether deadline isDone
     * @param by Time/Date of deadline
     * @throws DukeException Throws if given invalid date/time
     */
    public Deadline(String description, boolean isDone, String by) throws DukeException {
        super(description, isDone);
        try {
            String[] split = by.split(" ");
            this.by = LocalDate.parse(split[0]);
            if (split.length > 1) {
                time = LocalTime.parse(split[1]);
            } else {
                time = null;
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date");
        }
    }

    /**
     * Getter for time/date of deadline
     *
     * @return time/date
     */
    public String getBy() {
        return by.toString();
    }

    /**
     * Overloaded toString function which returns string representation of Deadline
     *
     * @return String representation of deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + (
                time != null ? (" " + time.format(DateTimeFormatter.ofPattern("HH:mm"))) : "") + ")";
    }

    /**
     * Overloaded getFileString function which returns a string representation of Deadline
     * such that the parser is able to read or write the contents into a file
     *
     * @return Parser-enabled string representation of Deadline
     */
    @Override
    public String getFileString() {
        return "D|" + (isDone == true ? "1|" : "0|") + getDescription() + "|" + getBy() + (
                time != null ? (" " + time) : "") + "\n";
    }
}
