package duke.tasks;

import duke.tasks.Task;

import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate deadlineBy;

    public Deadline(String description, LocalDate deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
    }

    public LocalDate getDeadlineBy() {
        return deadlineBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineBy + ")";
    }
}
