package duke.modules;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * Represents a Task of type Deadline which includes a due date.
 */
class Deadline extends Task {

    private final LocalDate dueDate;

    /**
     * Constructor for a Deadline.
     *
     * @param name The name of the task.
     * @param dueDate The date the task has to be completed by in the format "yyyy-mm-dd".
     */
    public Deadline(String name, String dueDate) throws DateTimeParseException {
        super(name);
        this.dueDate = LocalDate.parse(dueDate);
    }

    @Override
    public String toString() {
        if (super.getStatus() == 1) {
            return "[D][X] " + super.getName() + " (by: "
                    + dueDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + ")";
        } else {
            return "[D][ ] " + super.getName() + " (by: "
                    + dueDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + ")";
        }
    }
}
