package seedu.task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    private final LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, boolean isCompleted, LocalDateTime deadline) {
        super(description, isCompleted);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(formatter) + ")";
    }
}
