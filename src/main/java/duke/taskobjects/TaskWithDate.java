package duke.taskobjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An abstract class representing a Task with dates.
 */
public abstract class TaskWithDate extends Task {
    private LocalDate date;

    /**
     * Creates a TaskWithDate object.
     *
     * @param name Task name or description.
     * @param date Additional date variable.
     */
    public TaskWithDate(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /**
     * Creates a TaskWithDate object.
     * Alternative constructor used for importing existing tasks.
     *
     * @param name Task name or description.
     * @param isDone Boolean which shows if the task is marked as done or not.
     * @param date Additional date variable.
     */
    public TaskWithDate(String name, boolean isDone, String date) {
        super(name, isDone);
        this.date = LocalDate.parse(date);
    }

    public void changeDate(LocalDate newDate) {
        this.date = newDate;
    }

    /**
     * Returns a formatted String representation of the date.
     *
     * @return The String representation of the date.
     */
    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return date.format(formatter);
    }

    /**
     * Returns a String representation of the date (Java's DateTime default).
     *
     * @return The String representation of a date.
     */
    public String getDate() {
        return date.toString();
    }
}
