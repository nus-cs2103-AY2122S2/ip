package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of Task class.
 * Denotes a task that is meant to be done by a specific day and time
 */
public class Deadline extends Task {
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    protected String date;
    protected LocalDateTime time;

    public Deadline(String description, String time) {
        super(description);
        this.time = LocalDateTime.parse(time, inputFormatter);
        this.date = time.split(" ",2)[0];
    }

    /**
     * Outputs the formatted time related to the task
     * @return a String in  MMM dd yyyy, HH:mm format
     */
    public String outputTime() {
        return time.format(outputFormatter);
    }

    /**
     * Outputs the time related to the task in the format that it was initially entered by the user.
     * @return a String in format: dd/MM/yyyy HHmm
     */
    public String displayTimeInOriginalFormat() {
        return time.format(inputFormatter);
    }

    public void setTime(String newTime) {
        String currDate = this.date;
        String snoozedTime = this.date + " " + newTime;
        this.time = LocalDateTime.parse(snoozedTime, inputFormatter);
    }

    public void setDate(String newDate) {
        this.date = newDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + outputTime() + ")";
    }
}
