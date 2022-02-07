package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task that is happening on the specified date.
 */
public class EventTask extends Task {
    private LocalDate eventDate;

    /**
     * Constructor for EventTask.
     * @param taskName The name of the Task to be added.
     * @param isDone If the Task that is being added is marked.
     * @param date The date of the Task.
     */
    public EventTask(String taskName, boolean isDone, LocalDate date) {
        this.taskName = taskName;
        this.isDone = isDone;
        this.eventDate = date;
    }

    public LocalDate getDate() {
        return this.eventDate;
    }

    @Override
    public String toFileString() {
        return String.format("%s,%s,%s,%s",
                "E",
                this.isDone ? "T" : "F",
                this.taskName,
                this.eventDate.format(DateTimeFormatter.ofPattern("dd/M/yyyy")));
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at:%s)", this.isDone ? "X" : " ", this.taskName,
                this.eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

}
