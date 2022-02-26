package model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task{
    private final LocalDate deadlineTime;

    public DeadlineTask(String task, String deadlineTime) throws DateTimeParseException {
        super(task);
        this.deadlineTime = LocalDate.parse(deadlineTime);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + deadlineTime + ")";
    }
}
