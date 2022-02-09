package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class is a type of Task which is used to represent a task that has a deadline.
 */
public class Deadline extends Task implements Comparable<Task> {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the string representation of this deadline.
     *
     * @return the string representation of this deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns the save format of this task.
     *
     * @return A String representing the save format of this task.
     */
    @Override
    public String getSaveFormat() {
        return "D," + ((isDone ? "1" : "0")) + "," + super.getSaveFormat() + "," + this.by.toString();
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof Deadline) {
            return this.by.compareTo(((Deadline) o).by) + this.description.compareTo(o.description);
        } else if (o instanceof Todo || o instanceof Event) {
            return -1;
        }
        return 0;
    }
}
