package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


class Event extends Task {

    private final LocalDate time;

    public Event(String name, LocalDate time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        if (super.getStatus() == 1) {
            return "[E][X] " + super.getName() + " (at: "
                    + time.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + ")";
        } else {
            return "[E][ ] " + super.getName() + " (at: "
                    + time.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + ")";
        }
    }
}
