package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Creates a Deadline with the given description and deadline.
     *
     * @param description The name of the Deadline task.
     * @param deadline The deadline of the Deadline task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return String.format("%s (by: %shrs)", super.toString(), deadline.format(format));
    }

    @Override
    public String getFileSaveFormat() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return String.format("%s | %s", super.getFileSaveFormat(), deadline.format(format));
    }
}
