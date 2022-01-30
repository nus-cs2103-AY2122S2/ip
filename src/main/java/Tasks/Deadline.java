package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate deadline;

    public Deadline(String description, String deadline) {
        super(description);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (Exception e) {
            this.deadline = null;
        }
    }

    @Override
    public String toString() {
        return " [D]" + super.toString() + "\t (by " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}