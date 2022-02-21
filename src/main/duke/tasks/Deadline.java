package main.duke.tasks;

import main.duke.enums.TaskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime dueDate;

    public Deadline(String description, LocalDateTime dueDate) {
        super(description, TaskType.DEADLINE);
        this.dueDate = dueDate;
    }

    public Deadline(String description, LocalDateTime dueDate, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.dueDate = dueDate;
    }

    public LocalDateTime getDueDate() {
        return this.dueDate;
    }

    @Override
    public Task clone() {
        return new Deadline(this.getDescription(), this.getDueDate(), this.getIsDone());
    }

    @Override
    public String toStoreString() {
        return super.toStoreString() + "~" + this.getDueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)",
                this.getDueDate().format(DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm")));
    }
}
