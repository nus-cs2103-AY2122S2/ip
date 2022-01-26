package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * General class encompassing all the common functionalities of all children tasks
 */
public class Task {
    String task;
    Boolean done;
    String initials;

    /**
     * Constructor for General Task
     * @param task description of the task
     */
    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * Function to mark the current task as done
     */
    public void mark() {
        this.done = true;
    }

    /**
     * function to mark the current task as not done
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * Converts the task into a readable form
     * @return String containing description of current task
     */
    @Override
    public String toString() {
        String string;

        if (this.done) {
            string = "[X] " + task;
        } else {
            string = "[ ] " + task;
        }

        return string;
    }

    /**
     * Converts the task into a compact version for storage
     * @return String containing compact version of the task
     */
    public ArrayList<String> makeCompact() {
        ArrayList<String> out = new ArrayList<>();

        out.add(this.initials);
        if (done) {
            out.add("1");
        } else {
            out.add("0");
        }
        out.add(task);
        return out;
    }

    /**
     * Converts the String representation of Date and time to a LocalDateTime object
     * @param date String representation for the Date and time
     * @return a LocalDateTime object representing the Date and time of this Task
     */
    public static LocalDateTime formatDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        date = date.trim();
        try {
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Please follow the required format for dates:\nyyyy/MM/dd HH:mm");
        }
        return null;
    }
}
