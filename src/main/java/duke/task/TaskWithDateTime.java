package duke.task;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a task with an associated date and/or time.
 *
 * Note: the allowable formats for date and time to be interpreted correctly
 * by TaskWithDateTime is YYYY/MM/DD (with ./| being valid separators) and HHMM.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 1.0
 */
public class TaskWithDateTime extends Task {
    private LocalDate day;
    private String dateTime;
    private String originalDateTime;

    /**
     * Constructor for TaskWithDateTime.
     * Specifies description and dateTime.
     *
     * @param description description of task
     * @param dateTime datetime associated with task in string format
     */
    public TaskWithDateTime(String description, String dateTime) {
        super(description);

        dateTime = dateTime.trim();
        this.dateTime = dateTime;
        this.originalDateTime = dateTime;

        extractTime(extractDate());
    }

    /**
     * Formats date in dateTime correctly (if any).
     * Dates detected are of the format YYYYMMDD delimited by -|/.
     * and are changed to MMM d yyyy format.
     *
     * @return string with date removed.
     */
    public String extractDate(){
        String regexDate = "\\d{4}[-|/.]\\d{2}[-|/.]\\d{2}";
        Matcher m = Pattern.compile(regexDate).matcher(dateTime);
        String strWithoutDate = dateTime;

        // If specified date format is found
        if (m.find()) {
            strWithoutDate = strWithoutDate.replace(m.group(0), "");

            try {
                day = LocalDate.parse(m.group(0).replaceAll("[./|]", "-"));
                dateTime = dateTime.replace(m.group(0),
                        day.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            } catch (DateTimeException e) {
                // Set date to the next day if date is invalid
                dateTime = dateTime.replace(m.group(0),
                        LocalDate.now().plus(1, ChronoUnit.DAYS).toString());
            }
        }

        return strWithoutDate;
    }

    /**
     * Formats time in dateTime correctly (if any).
     * Time detected is of the format HHMM, and is changed to HH:MM.
     * Note: input string must not contain any dates or other four
     * contiguous digits.
     *
     * @param str time string associated with task
     */
    public void extractTime(String str) {
        String regexTime = "\\d{4}";
        Matcher m = Pattern.compile(regexTime).matcher(str);

        // If specified time format is found
        if (m.find()) {
            try {
                LocalTime timeOfDay = LocalTime.parse(m.group(0).substring(0, 2)
                        + ":" + m.group(0).substring(2));
                dateTime = dateTime.replace(m.group(0), timeOfDay.toString());
            } catch (DateTimeException e) {
                // Set time to an hour from now if time is invalid
                dateTime = dateTime.replace(m.group(0),
                        LocalTime.now().plus(1, ChronoUnit.HOURS).toString());
            }
        }
    }

    /**
     * Default toString method that returns formatted TaskWithDateTime.
     *
     * @return formatted string of description, dateTime and completion
     * status of the TaskWithDateTime object.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + dateTime.trim() + ")";
    }

    /**
     * Parses contents of TaskWithDateTime into a csv-like format.
     * Delimiter is '|'.
     *
     * @return formatted string of TaskWithDateTime, its completion
     * status and associated dateTime.
     */
    @Override
    public String writeToFile() {
        return super.writeToFile() + " | " + originalDateTime;
    }
}