package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.temporal.ChronoUnit;

/**
 * Represents a task with an associated date and/or time.
 *
 * Note: the allowable formats for date and time to be interpreted correctly by TaskWithDateTime
 * is YYYY/MM/DD (with ./| being valid separators) and HHMM.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 0.1
 */
public class TaskWithDateTime extends Task {
    protected LocalDate day;
    protected LocalTime timeOfDay;
    protected String dateTime;

    /**
     * Constructor for TaskWithDateTime that initializes the Task with a given description and dateTime.
     *
     * @param description description of task
     * @param dateTime datetime associated with task in string format
     */
    public TaskWithDateTime(String description, String dateTime) {
        super(description);

        String temp = dateTime.trim();
        this.dateTime = dateTime.trim();

        //extract date, if any
        String regexDate = "\\d{4}[-|/.]\\d{2}[-|/.]\\d{2}";
        Matcher m = Pattern.compile(regexDate).matcher(dateTime);
        if (m.find()) {
            try {
                day = LocalDate.parse(m.group(0).replaceAll("[./|]","-"));
                dateTime = dateTime.replace(m.group(0),
                        day.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
                this.dateTime = dateTime;

                temp = temp.replace(m.group(0), "");
            } catch (DateTimeException e) {
                System.out.println("The date specified is invalid and has automatically been changed to tomorrow.");
                this.dateTime = dateTime.replace(m.group(0), LocalDate.now().plus(1, ChronoUnit.DAYS).toString());
                temp = temp.replace(m.group(0), "");
            }

        }

        //extract time, if any
        String regexTime = "\\d{4}";
        m = Pattern.compile(regexTime).matcher(temp);
        if (m.find()) {
            try {
                timeOfDay = LocalTime.parse(m.group(0).substring(0,2) + ":" + m.group(0).substring(2));
                this.dateTime = this.dateTime.replace(m.group(0), timeOfDay.toString());
            } catch (DateTimeException e) {
                System.out.println("The time specified is invalid and has automatically been changed to an hour from now.");
                this.dateTime = this.dateTime.replace(m.group(0),
                        LocalTime.now().plus(1, ChronoUnit.HOURS).toString());
            }
        }
    }

    /**
     * Default toString method that returns a formatted string of the contents of TaskWithDateTime
     *
     * @return formatted string of description, dateTime and completion status of the TaskWithDateTime object
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + dateTime.trim() + ")";
    }

    /**
     * Parses contents of TaskWithDateTime into a csv-like format delimited by '|'
     *
     * @return formatted string of TaskWithDateTime, its completion status and associated dateTime
     */
    @Override
    public String writeToFile() {
        return super.writeToFile() + " | " + dateTime.trim();
    }
}
