package task;

import task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected LocalDateTime datetime = null;
    protected LocalDate date = null;

    public static final String DATETIME_FORMAT = "yyyy-MM-dd HHmm";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_OUTPUT_FORMAT = "MMM dd yyyy HHmm";
    public static final String TIME_OUTPUT_FORMAT = "MMM dd yyyy";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        convertToDate(by);
    }

    private void convertToDate(String by) {
        try {
            String[] split = by.split("\\s+");
            if (split.length > 1) {
                this.datetime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(DATETIME_FORMAT));
            } else {
                this.date = LocalDate.parse(by, DateTimeFormatter.ofPattern(DATE_FORMAT));
            }
        }
        /* Date format is invalid */
        catch (DateTimeParseException e) {
            System.out.println(by + " is Invalid Date & Time format");
        }
    }

    public String printDeadline() {
        if (this.datetime != null) {
            return datetime.format(DateTimeFormatter.ofPattern(DATETIME_OUTPUT_FORMAT));
        } else {
            return date.format(DateTimeFormatter.ofPattern(TIME_OUTPUT_FORMAT));
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + printDeadline() + ")";
    }
}