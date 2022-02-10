package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a deadline which is a more specific case of a task.
 */
public class Deadline extends Task {

    protected String time;
    protected LocalDate localDate;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public Deadline(String description, String time, boolean isDone) {
        super(description);
        this.time = time;
        this.isDone = isDone;
    }

    public Deadline(String description, LocalDate localDate) {
        super(description);
        this.localDate = localDate;
    }

    public Deadline(String description, LocalDate localDate, boolean isDone) {
        super(description);
        this.localDate = localDate;
        this.isDone = isDone;
    }
    /**
     * Returns the String representation of the deadlne, including its type as [D] and the deadline time.
     * @return Return the String representation of the task.
     */
    @Override
    public String toString() {
        if (localDate == null) {
            return "[D]" + super.toString() + " (by: " + time + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + localDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy").withLocale(Locale.ENGLISH)) + ")";
        }

    }

    /**
     * Returns the detail of the task.
     * @return the detail of the task.
     */
    @Override
    public String getDetail(){
        int status = isDone ? 1 : 0;
        String t = this.time == null ? localDate.toString() : this.time;
        return "D" + " | " + status + " | " + this.description + " | " + t + "\n";
    }
}
