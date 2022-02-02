package seedu.duke;

import java.time.LocalDate;

/**
 * A Task that specifies the date required for the task to be completed
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
        String tempStr = " ";

        if (this.isDone) {
            tempStr = "X";
        }

        return "[D][" + tempStr + "] " + this.description + "(" + this.time.get().format(dateFormatter) + ")";
    }

}
