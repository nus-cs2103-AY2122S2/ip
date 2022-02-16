package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.utils.Priority;

public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Creates a Deadline with the given description and deadline.
     *
     * @param description The name of the Deadline task.
     * @param deadline The deadline of the Deadline task.
     * @param priority The Priority object specifying the priority of this Deadline.
     */
    public Deadline(String description, LocalDateTime deadline, Priority priority) {
        super(description, TaskType.DEADLINE, priority);
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
