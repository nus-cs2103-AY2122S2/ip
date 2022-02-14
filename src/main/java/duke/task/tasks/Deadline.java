package duke.task.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate deadline;
    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = LocalDate.parse(deadline);
    }

    public Deadline(String taskName, boolean isDone, String deadline) {
        super(taskName, isDone);
        this.deadline = LocalDate.parse(deadline);
    }

    public Deadline(String taskName, boolean isDone, LocalDate deadline) {
        super(taskName, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)",
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String encode() {
        return "D <> " + super.encode() + " <> " + deadline + "\n";
    }

    @Override
    public Deadline cloneSelf() {
        return new Deadline(getTaskName(), isDone(), this.deadline);
    }
}
