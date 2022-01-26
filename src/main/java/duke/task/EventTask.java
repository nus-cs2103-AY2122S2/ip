package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task that is happening on the specified date.
 */
public class EventTask extends Task{
    private LocalDate d;

    public EventTask(String ss, LocalDate date) {
        this.taskName = ss;
        this.isDone = false;
        this.d = date;
    }
    public EventTask(String ss, boolean isDone, LocalDate date) {
        this.taskName = ss;
        this.isDone = isDone;
        this.d = date;
    }

    public String toFileString() {
        return String.format("%s,%s,%s,%s",
                'E',
                this.isDone,
                this.taskName, this.d.format(DateTimeFormatter.ofPattern("dd/M/yyyy")));
    }

    public LocalDate getDate() {
        return this.d;
    }
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at:%s)", this.isDone?"X":" ", this.taskName,
                this.d.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

}
