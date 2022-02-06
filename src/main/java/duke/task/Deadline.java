package duke.task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline. A Deadline is a type of task that must be done by a certain date and time.
 */
public class Deadline extends Task{
    protected LocalDate date;
    protected LocalTime time;
    private DateTimeFormatter dateOutputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private DateTimeFormatter timeOutputFormatter = DateTimeFormatter.ofPattern("HHmm");

    /**
     * Constructor for Deadline.
     *
     * @param name Name of Deadline.
     * @param date Date to complete Deadline by.
     * @param time Time to complete Deadline by.
     */
    public Deadline(String name, LocalDate date, LocalTime time) {
        super("D", name, date, time);
        this.date = date;
        this.time = time;
    }

    /**
     * Overrides toString to display type of Task, whether or not it is completed, its name,
     * and by when to complete Deadline.
     *
     * @return String containing above information
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(dateOutputFormatter) + " " +
                time.format(timeOutputFormatter) + ")";
    }
}
