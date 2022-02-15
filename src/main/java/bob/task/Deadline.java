package bob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * {@inheritDoc}
 */
public class Deadline extends Task {
    private LocalDateTime date;

    /**
     * Constructor for a task with a deadline.
     * @param name name of the task
     * @param dateTime dateTime of the deadline
     */
    public Deadline(String name, LocalDateTime dateTime) {
        super(name);
        super.setType("D");
        super.unmarkTask();
        this.date = dateTime;
    }

    public LocalDateTime getDateTime() {
        return date;
    }

    @Override
    public String printStatus() {
        return "[D] " + super.getStatusSymbol() + " " + this + "\n\t(by: "
                + date.format(DateTimeFormatter.ofPattern("dd MMM yyy HH:mm")) + ")";
    }

    @Override
    public String toString() {
        return super.getName();
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof Todo) {
            return -1;
        } else if (o instanceof Deadline) {
            Deadline dl = (Deadline) o;
            return this.date.compareTo(dl.getDateTime());
        } else {
            Event event = (Event) o;
            return this.date.compareTo(event.getDateTime());
        }
    }
}
