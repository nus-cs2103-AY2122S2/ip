package duke;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task. A <code>Deadline</code> object corresponds to
 * a Task which contains a <code>LocalDateTime</code> field.
 */

public class Deadline extends Task {
    private String deadline;
    private LocalDateTime dateTime;

    /**
     * Constructor for Deadline object.
     * @param name Name of deadline task
     * @param deadline Deadline in yyyy/mm/dd HHmm format
     */
    public Deadline(String name, String deadline) {
        super(name, "D", deadline);
        this.deadline = deadline;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        this.dateTime = LocalDateTime.parse(deadline, formatter);
    }

    private String createDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mma");
        String time = dateTime.format(formatter);
        String suffix = getDayOfMonthSuffix(dateTime.getDayOfMonth());
        return String.valueOf(dateTime.getDayOfMonth()) + suffix + " " +
                dateTime.getMonth().toString().substring(0,1) +
                dateTime.getMonth().toString().substring(1).toLowerCase()
                + " " + dateTime.getYear() + ", " + time;
    }

    public void setTime(String time) {
        this.deadline = time;
        this.setExtension(deadline);
        System.out.println(this.saveString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        this.dateTime = LocalDateTime.parse(deadline, formatter);
    }

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
        return "[D]" + super.toString() + " (by: " + this.createDateString() + ")";
    }

}
