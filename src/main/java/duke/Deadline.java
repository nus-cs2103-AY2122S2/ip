package duke;

import java.time.LocalDate;

/**
 * A Task that specifies the date required for the task to be completed. Supports Task description and time
 */

public class Deadline extends Task {

    Deadline(String description, LocalDate deadline) {
        super(description, deadline);
    }

    Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone, deadline);
    }

    @Override
    public String toString() {

        String doneIndicator;
        if (isDone) {
            doneIndicator = "X";
        } else {
            doneIndicator = " ";
        }

        return "[D][" + doneIndicator + "] "
                + description 
                + "(" + time.get().format(DATE_FORMATTER) + ")";
    }

}
