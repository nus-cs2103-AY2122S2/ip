package seedu.duke;

import java.time.LocalDate;

public class Deadline extends Task {

    Deadline(String description, LocalDate deadline) {
        super(description, deadline);
    }

    Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone, deadline);
    }

    @Override
    public String toString() {
        String tempStr = " ";

        if (isDone) {
            tempStr = "X";
        }

        return "[D][" + tempStr + "] " + description + "(" + time.get().format(dateFormatter) + ")";
    }

}
