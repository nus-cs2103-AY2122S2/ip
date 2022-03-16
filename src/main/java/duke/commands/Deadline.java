package duke.commands;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Represents a Deadline to be met by the user with a specified date and/or time.
 */
public class Deadline extends Task {
    private static final String[] DATE_FORMATS = {
        "MMM dd yyyy",
        "dd MM yyyy",
        "yyyy/MM/dd",
        "yyyy MM dd",
    };
    private static final String[] TIME_FORMATS = {
        "HH:mm",
        "HHmm",
    };
    private String by;
    private LocalDate date;
    private LocalTime time;
    private String dateFormat;
    private String timeFormat;

    /**
     * Constructs a Deadline object.
     *
     * @param description description of Deadline.
     * @param by date and/or time of Deadline.
     */
    public Deadline(String description, String by) {
        super(description, "D");
        this.by = by;
    }

    /**
     * Checks if time is given by user.
     *
     * @return True if time is given by user, False otherwise
     */
    public boolean hasTime() {
        return (this.by.split(" ").length != 1);
    }
    /**
     * Checks if time given is in valid format and sets the timeFormat attribute.
     *
     * @return True if time is given in a recognised format, False otherwise
     * @throws DateTimeParseException If parser could not parse the time according to format.
     */
    public boolean isValidTime() {
        for (String format : TIME_FORMATS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                LocalTime.parse(this.by.split(" ")[1], formatter);
                this.timeFormat = format;
                return true;
            } catch (DateTimeParseException e) {
                e.getMessage();
            }
        }
        return false;
    }

    /**
     * Checks if date given is in valid format and sets the dateFormat attribute.
     *
     * @return True if date is given in a recognised format, False otherwise
     * @throws DateTimeParseException If parser could not parse the date according to format.
     */
    public boolean isValidDate() {
        String d = this.by.split(" ")[0].replace("-", " ");
        for (String format : DATE_FORMATS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH);
                LocalDate.parse(d, formatter);
                this.dateFormat = format;
                return true;
            } catch (DateTimeParseException e) {
                e.getMessage();
            }
        }
        return false;
    }

    /**
     * Returns formatted date of Deadline.
     *
     * @return date of Deadline.
     */
    @Override
    public String getDate() {
        String d = this.by.split(" ")[0].replace("-", " ");
        if (this.isValidDate()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.dateFormat, Locale.ENGLISH);
            this.date = LocalDate.parse(d, formatter);
            return this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            return "";
        }
    }

    /**
     * Returns formatted time of Deadline.
     *
     * @return time of Deadline.
     */
    @Override
    public String getTime() {
        if (hasTime() && isValidTime()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.timeFormat);
            this.time = LocalTime.parse(this.by.split(" ")[1], formatter);
            return this.time.format(DateTimeFormatter.ofPattern("HH:mm a"));
        } else {
            return "";
        }
    }

    /**
     * Returns a formatted String describing the Deadline.
     *
     * @return formatted String describing the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:  " + (this.getDate() + " " + this.getTime()).trim() + ")";
    }
}

