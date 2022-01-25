package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.task.Task;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, LocalDate date) {
        super(description);
        by = date;
    }

    @Override
    public String toFileFormat() {
        return "D," + super.toFileFormat() + "," + by + "," + getStatusIcon();
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " 
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}