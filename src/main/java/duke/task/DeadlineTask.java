package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task that should be completed before the specified date.
 */
public class DeadlineTask extends Task{
    private LocalDate d;

    public DeadlineTask(String taskName, LocalDate date) {
        this.taskName = taskName;
        this.isDone = false;
        this.d = date;
    }

    public DeadlineTask(String taskName, boolean isDone, LocalDate date) {
        this.taskName = taskName;
        this.isDone = isDone;
        this.d = date;
    }

    public LocalDate getDueDate() {
        return this.d;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by:%s)", this.isDone?"X":" ", this.taskName,
                this.d.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
