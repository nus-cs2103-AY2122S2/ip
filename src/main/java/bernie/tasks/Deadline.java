package bernie.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a Deadline class for tasks that needs to be done before a timing
     * @param description String
     * @param by String, the deadline to meet by
     */
    Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    String printDate() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), printDate());
    }
}
