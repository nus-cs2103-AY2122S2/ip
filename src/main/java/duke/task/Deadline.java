package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;


/**
 * Deadline represents a task that the user has to to by a certain time.
 *
 * @author Jian Rong
 */

public class Deadline extends Task {
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructor of Deadline class.
     * @param title Title of Deadline
     * @param date Date of Deadline
     * @param time Time of Deadline
     */
    public Deadline(String title, LocalDate date, LocalTime time) {
        super(title);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns a summary of the Deadline Task.
     * @return The summary of the Deadline.
     */
    public String toString() {
        int day = date.getDayOfMonth();
        Month month = date.getMonth();
        int year = date.getYear();
        String result = "";
        switch (this.priority) {
            case LOW:
                result += "[L]";
                break;
            case MEDIUM:
                result += "[M]";
                break;
            case HIGH:
                result += "[H]";
                break;
        }
        if (this.isChecked) {
            result += "[D][X]";
        } else {
            result += "[D][ ]";
        }
        return result + String.format(" %s (by: %d %s %d %s)", title, day, month, year, time);
    }
}
