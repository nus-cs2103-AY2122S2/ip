package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

class Deadline extends Task {

    private final LocalDate dueDate;

    public Deadline(String name, String dueDate) {
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
