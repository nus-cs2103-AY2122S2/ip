package duke;

import java.time.LocalDate;

/**
 * A Task that specifies a date of occurrence.
 */
public class Event extends Task {

    Event(String description, LocalDate time) {
        super(description, time);
    }

    Event(String description, boolean isDone, LocalDate time) {
        super(description, isDone, time);
    }

    @Override
    public String toString() {
        String tempStr = " ";

        if (isDone) {
            tempStr = "X";
        }

        return "[E][" + tempStr + "] " 
                + description + "(" 
                + time.get().format(DATE_FORMATTER) + ")";
    }
}

