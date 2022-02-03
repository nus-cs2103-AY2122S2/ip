package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class that belongs to the Tasks Package.
 * This class encapsulates the logic of how Tasks should be represented in Duke.
 */
public abstract class Tasks {

    private final String task;
    private Boolean marked;

    /**
     * Constructor for Tasks.
     * @param task Task that should be recorded.
     * @param marked Boolean flag for whether a task is completed.
     */
    public Tasks(String task, Boolean marked) {
        this.task = task;
        this.marked = marked;
    }

    /**
     * Sets {@link #marked} to the specified value.
     * @param var Boolean to mark a task as completed/not completed.
     */
    public void setMarked(Boolean var) {
        this.marked = var;
    }

    /**
     * Gets {@link #task}
     * @return {@link #task}.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Gets {@link #marked}.
     * @return {@link #marked}.
     */
    public boolean getMarked() {
        return this.marked;
    }


    /**
     * Abstract method to specify how a String representation of Task should be cached into
     * a file.
     * @return
     */
    public abstract String cacheString();

    /**
     * Converts a date of format "yyyy-MM-dd" to "MMM dd yyyy".
     * @param s String representation of a date.
     * @return Date that follows the format if applicable.
     */
    public static String returnDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        try {
            LocalDate dateTime = LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return dateTime.format(formatter);
        } catch (DateTimeParseException e) {
            return s;
        }
    }

    /**
     * Converts Tasks to a String representation.
     * @return Task as a string.
     */
    public abstract String toString();
}
