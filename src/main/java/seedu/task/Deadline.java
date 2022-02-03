package seedu.task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    private final LocalDateTime deadline;

    /**
     * Constructor for Deadline class
     * @param description Description of Task
     * @param deadline Deadline of task
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructor for Deadline class
     * @param description Description of Task
     * @param isCompleted Marks whether task is completed or not
     * @param deadline Deadline of task
     */
    public Deadline(String description, boolean isCompleted, LocalDateTime deadline) {
        super(description, isCompleted);
        this.deadline = deadline;
    }

    @Override
    public String toFile() {
        return "D\t" + super.toFile() + "\t" + deadline.format(FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(FORMATTER) + ")";
    }
}
