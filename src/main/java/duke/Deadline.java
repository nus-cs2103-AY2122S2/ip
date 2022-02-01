package main.java.duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs an instance of Deadline.
     *
     * @param description Description of task.
     * @param by Deadline which task must be completed by.
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.by = LocalDateTime.parse(by, formatter);
    }

    /**
     * Converts Deadline to string format.
     *
     * @return Deadline in string format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    /**
     * Converts Deadline to file format.
     *
     * @return Deadline in file format.
     */
    public String toFileFormat() {
        return "D," + String.valueOf(isDone) + "," + description + ","
                + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
