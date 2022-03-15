package duke.tasks;

import duke.exceptions.DukeException;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Event Task. A <code>Event</code> object corresponds to
 * a Task which contains 2 <code>LocalDateTime</code> fields for start and end respectively.
 */

public class Event extends Task {
    private String event;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;


    /**
     * Constructor which takes in the name, the start and the end time in YYYY/MM/DD HHMM format.
     * @param name
     * @param start
     * @param end
     * @throws DukeException
     */
    public Event(String name, String start, String end) throws DukeException {
        super(name, "E", start + " " + end);
        this.event = start + " " + end;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.startDateTime = LocalDateTime.parse(start, formatter);
        this.endDateTime = LocalDateTime.parse(end, formatter);
        if (!startDateTime.isBefore(endDateTime)) {
            throw new DukeException("The start date/time must be before the end date/time.");
        }
    }

    public String getStartString() {
        return this.event.split(" ")[0];
    }

    public String getEndString() {
        return this.event.split(" ")[1];
    }

    /**
     * Creates the relevant date string using the correct start and end time.
     * @param dateTime
     * @return appropriate date string.
     */
    private String createDateString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mma");
        String time = dateTime.format(formatter);
        String suffix = getDayOfMonthSuffix(dateTime.getDayOfMonth());
        return dateTime.getDayOfMonth() + suffix + " " +
                dateTime.getMonth().toString().substring(0,1) +
                dateTime.getMonth().toString().substring(1).toLowerCase()
                + " " + dateTime.getYear() + ", " + time;
    }

    /**
     * Utility method to get the suffix associated with the day of the month.
     * @param n
     * @return the correct suffix
     */
    private String getDayOfMonthSuffix(int n) {
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
        case 1:  return "st";
        case 2:  return "nd";
        case 3:  return "rd";
        default: return "th";
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + this.createDateString(startDateTime) + " to " +
               this.createDateString(endDateTime) + ")";
    }

}
