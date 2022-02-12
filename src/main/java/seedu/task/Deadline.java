package seedu.task;

import java.time.LocalDateTime;

/**
 * The Deadline Class of Task
 */
public class Deadline extends Task {

    private LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, boolean isCompleted, LocalDateTime deadline, int priority) {
        super(description, isCompleted, priority);
        this.deadline = deadline;
    }

    @Override
    public String toFile() {
        return "D\t" + super.toFile() + "\t" + deadline.format(DATE_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DATE_FORMAT) + ")";
    }
}
