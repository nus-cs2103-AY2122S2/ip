package juke.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

import juke.exception.JukeParseException;

/**
 * Abstraction for date and time with additional methods.
 */
public class DateTimeHandler {
    /**
     * Date and time.
     */
    private LocalDateTime dateTime;

    /**
     * Constructor that initializes a date and time from a string.
     *
     * @param string String to parse date and time.
     * @throws JukeParseException Throws if string is in wrong format.
     */
    public DateTimeHandler(String string) throws JukeParseException {
        this.parse(string);
    }

    /**
     * Tries to parse a string into date and time format.
     *
     * @param string String to parse.
     * @throws JukeParseException Throws if string is in wrong format.
     */
    private void parse(String string) throws JukeParseException {
        if (string == null || string.isEmpty()) {
            throw new JukeParseException("date and time");
        } else {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                    .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
                    .appendOptional(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")).toFormatter();
            try {
                this.dateTime = LocalDateTime.parse(string, formatter);
            } catch (DateTimeParseException e) {
                throw new JukeParseException("date and time");
            }
        }
    }

    /**
     * Returns dat and time in the right format.
     *
     * @return Formatted date and time string.
     */
    public String getDateTime() {
        String dt = "";
        if (this.dateTime != null) {
            dt += String.format("%td %tb %tY %tR", this.dateTime, this.dateTime, this.dateTime, this.dateTime);
        }
        return dt;
    }
}
