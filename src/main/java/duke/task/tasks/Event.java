package duke.task.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate time;

    public Event(String taskName, String time) {
        super(taskName);
        this.time = LocalDate.parse(time);
    }

    public Event(String taskName, boolean isDone, String time) {
        super(taskName, isDone);
        this.time = LocalDate.parse(time);
    }

    public Event(String taskName, boolean isDone, LocalDate time) {
        super(taskName, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)",
                this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String encode() {
        return "E <> " + super.encode() + " <> " + time + "\n";
    }

    @Override
    public Event cloneSelf() {
        return new Event(getTaskName(), isDone(), this.time);
    }
}
