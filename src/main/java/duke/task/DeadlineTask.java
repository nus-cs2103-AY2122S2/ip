package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task that should be completed before the specified date.
 */
public class DeadlineTask extends Task {
    private LocalDate deadlineDate;

    /**
     * Constructor for DeadlineTask.
     * @param taskName The name of the Task to be added.
     * @param isDone If the Task that is being added is marked.
     * @param date The date of the Task.
     */
    public DeadlineTask(String taskName, boolean isDone, LocalDate date) {
        this.taskName = taskName;
        this.isDone = isDone;
        this.deadlineDate = date;
    }

    public LocalDate getDueDate() {
        return this.deadlineDate;
    }

    @Override
    public String toFileString() {
        return String.format("%s,%s,%s,%s",
                "D",
                this.isDone ? "T" : "F",
                this.taskName,
                this.deadlineDate.format(DateTimeFormatter.ofPattern("dd/M/yyyy")));
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by:%s)", this.isDone ? "X" : " ", this.taskName,
                this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
