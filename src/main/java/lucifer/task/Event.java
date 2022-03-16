package lucifer.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Class Event which inherits from Task.
 * @author Yu Meng
 */
public class Event extends Task {
    /** date and time of task **/
    protected LocalDateTime dateTime;
    /** description of task **/
    protected String at;

    /**
     * Constructor for Event class.
     *
     * @param description the description of the task
     * @param dateTime the date and time of the task
     */
    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        this.at = formatter.format(this.dateTime);
    }

    /**
     * Format to output when saving task.
     */
    @Override
    public String saveFormat() {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String dateInString = this.dateTime.format(formatter1);
        return "E" + " |" + (this.isDone ? "1" : "0") + "| " + this.description + " | " + dateInString;
    }

    /**
     {@inheritdoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
