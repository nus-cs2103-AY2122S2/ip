package bernie.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is one of the Task that can be created by the user input
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a Deadline class for tasks that needs to be done before a timing
     * @param description String
     * @param by LocalDate, created with a String input of form "yyyy-mm-dd"
     */
    Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Formats the LocalDate to be of the form: MMM d yyyy: eg Jan 28 2022
     * @return String, the formatted date form
     */
    String formatDate() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), formatDate());
    }
}
