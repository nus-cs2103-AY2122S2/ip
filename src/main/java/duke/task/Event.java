package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event task that inherits from Task
 */
public class Event extends Task {
    protected LocalDate at;
    protected LocalTime time;

    /**
     * Default constructor of Event
     *
     * @param description Description of event
     * @param at          Time/Date of event
     * @throws DukeException Throws if given invalid date/time
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            String[] split = at.split(" ");
            this.at = LocalDate.parse(split[0]);
            if (split.length > 1) {
                time = LocalTime.parse(split[1]);
            } else {
                time = null;
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date/time");
        }
    }

    /**
     * Overloaded Event constructor that takes in extra isDone variable
     * to set whether event is done
     *
     * @param description Description of event
     * @param isDone      Whether event isDone
     * @param at          Time/Date of event
     * @throws DukeException Throws if given invalid date/time
     */
    public Event(String description, boolean isDone, String at) throws DukeException {
        super(description, isDone);
        try {
            String[] split = at.split(" ");
            this.at = LocalDate.parse(split[0]);
            if (split.length > 1) {
                time = LocalTime.parse(split[1]);
            } else {
                time = null;
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date/time");
        }
    }

    /**
     * Getter for time/date of event
     *
     * @return time/date
     */
    public String getAt() {
        return at.toString();
    }

    /**
     * Overloaded toString function which returns string representation of Event
     *
     * @return String representation of event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + (
                time != null ? (" " + time.format(DateTimeFormatter.ofPattern("HH:mm"))) : "") + ")";
    }

    /**
     * Overloaded getFileString function which returns a string representation of Event
     * such that the parser is able to read or write the contents into a file
     *
     * @return Parser-enabled string representation of Event
     */
    @Override
    public String getFileString() {
        return "E|" + (isDone == true ? "1|" : "0|") + getDescription() + "|" + getAt() + (
                time != null ? (" " + time) : "") + "\n";
    }
}
