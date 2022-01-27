package pikabot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate byDate;

    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " +
            this.byDate.format(DateTimeFormatter.ofPattern("d-MMM-yyyy")) + ")";
    }
}