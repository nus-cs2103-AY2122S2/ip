package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents an event which is a more specific instance of a Task.
 */
public class Event extends Task {
    protected String time;
    protected LocalDate localdate;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }
    public Event(String description, String time, boolean isDone) {
        super(description);
        this.time = time;
        this.isDone = isDone;
    }

    public Event(String description, LocalDate localDate) {
        super(description);
        this.localdate = localDate;
    }

    public Event(String description, LocalDate ld, boolean isDone) {
        super(description);
        this.localdate = ld;
        this.isDone = isDone;
    }

    /**
     * Returns the String representation of the event, including its type as [E] and when it happened.
     * @return Return the String representation of the task.
     */
    @Override
    public String toString() {
        if (localdate == null) {
            return "[E]" + super.toString() + " (at: " + time + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + localdate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy").withLocale(Locale.ENGLISH)) + ")";
        }
    }

    /**
     * Returns the detail of the task.
     * @return the detail of the task.
     */
    @Override
    public String getDetail(){
        int status = isDone ? 1 : 0;
        String t = this.time == null ? localdate.toString() : this.time;
        return "E" + " | " + status + " | " + this.description + " | " + t + "\n";
    }
}
