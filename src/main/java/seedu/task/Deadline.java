package seedu.task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    private LocalDateTime deadline;
    private static final String TYPE = "D";

    public Deadline(String description, LocalDateTime deadline) {
        super(description, TYPE);
        this.deadline = deadline;
    }

    public Deadline(String description, boolean isCompleted, LocalDateTime deadline, int priority) {
        super(description, isCompleted, priority, TYPE);
        this.deadline = deadline;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " by" + deadline.format(DATE_FORMAT);
    }


    @Override
    public String toFile() {
            return super.toFile() + "\t" + deadline.format(DATE_FORMAT);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline.format(DATE_FORMAT) + ")";
    }
}
