package main.duke.tasks;

import main.duke.enums.TaskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime dueDate;

    public Deadline(String description, String dueDate) {
        super(description, TaskType.DEADLINE);
        this.dueDate = LocalDateTime.parse(dueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public Deadline(String description, String dueDate, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.dueDate = LocalDateTime.parse(dueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public LocalDateTime getDueDate() {
        return this.dueDate;
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
