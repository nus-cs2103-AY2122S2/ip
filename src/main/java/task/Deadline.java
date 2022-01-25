package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns the save format in String of this task.Task object
     *
     * @return A String for the save format of this task.Task object
     */
    @Override
    public String getSaveFormat() {
        return "D," + ((isDone ? "1" : "0")) + "," + super.getSaveFormat() + "," + this.by.toString();
    }
}
