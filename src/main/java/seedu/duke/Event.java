package seedu.duke;

import java.time.LocalDate;

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

        return "[E][" + tempStr + "] " + description + "(" + time.get().format(dateFormatter) + ")";
    }
}

