package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task that should be completed before the specified date.
 */
public class DeadlineTask extends Task {
    private LocalDate d;

    /**
     * Constructor for DeadlineTask.
     * @param taskName The name of the Task to be added.
     * @param isDone If the Task that is being added is marked.
     * @param date The date of the Task.
     */
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
        return String.format("[D][%s] %s (by:%s)", this.isDone ? "X" : " ", this.taskName,
                this.d.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
