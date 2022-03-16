package lucifer.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Class Deadline which inherits from Task.
 * @author Yu Meng
 */
public class Deadline extends Task {
    /** date and time of task **/
    protected LocalDateTime dateTime;
    /** description of task **/
    protected String by;

    /**
     * Constructor for Deadline class.
     *
     * @param description the description of the task
     * @param dateTime the date and time of the task
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        this.by = formater.format(this.dateTime);
    }

    /**
     * Format to output when saving task.
     */
    @Override
    public String saveFormat() {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String dateInString = this.dateTime.format(formatter1);
        return "D" + " |" + (this.isDone ? "1" : "0") + "| " + this.description + " | " + dateInString;
    }

    /**
     {@inheritdoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}

