package duke.tasks;

import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate deadlineBy;

    /**
     * Deadline constructor
     * @param description user input for deadline description
     * @param deadlineBy validated deadline with type LocalDate
     */
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
