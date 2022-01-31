package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline which is a more specific case of a task.
 */
public class Deadline extends Task {

    protected String time;
    protected LocalDate ld;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public Deadline(String description, String time, boolean isDone) {
        super(description);
        this.time = time;
        this.isDone = isDone;
    }

    public Deadline(String description, LocalDate ld) {
        super(description);
        this.ld = ld;
    }

    public Deadline(String description, LocalDate ld, boolean isDone) {
        super(description);
        this.ld = ld;
        this.isDone = isDone;
    }
    /**
     * Returns the String representation of the deadlne, including its type as [D] and the deadline time.
     * @return Return the String representation of the task.
     */
    @Override
    public String toString() {
        if(ld == null) {
            return "[D]" + super.toString() + " (by: " + time + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + ld.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")) + ")";
        }

    }

    /**
     * Returns the detail of the task.
     * @return the detail of the task.
     */
    @Override
    public String getDetail(){
        int status = isDone ? 1 : 0;
        String t = this.time == null ? ld.toString() : this.time;
        return "D" + " | " + status + " | " + this.description + " | " + t + "\n";
    }
}
